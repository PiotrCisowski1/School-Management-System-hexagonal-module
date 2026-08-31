package pl.cisowski.application.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cisowski.application.mappers.ScheduleMapper;
import pl.cisowski.domain.exceptions.ScheduleAppointmentConflictException;
import pl.cisowski.domain.model.command.CreateScheduleCommand;
import pl.cisowski.domain.model.external.Classroom;
import pl.cisowski.domain.model.external.Subject;
import pl.cisowski.domain.model.external.Teacher;
import pl.cisowski.domain.model.external.Yearbook;
import pl.cisowski.domain.model.schedule.Schedule;
import pl.cisowski.domain.model.schedule.ScheduleVersion;
import pl.cisowski.domain.ports.incoming.CreateSchedulePort;
import pl.cisowski.domain.ports.outgoing.*;
import pl.cisowski.domain.ports.outgoing.providers.ClassroomProviderPort;
import pl.cisowski.domain.ports.outgoing.providers.SubjectProviderPort;
import pl.cisowski.domain.ports.outgoing.providers.TeacherProviderPort;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CreateScheduleService implements CreateSchedulePort {

    private final ScheduleVersionRepositoryPort scheduleVersionRepository;
    private final ScheduleVersionEnricherPort scheduleVersionEnricher;
    private final ScheduleMapper scheduleMapper;
    private final SubjectProviderPort subjectProviderPort;
    private final TeacherProviderPort teacherProviderPort;
    private final ClassroomProviderPort classroomProviderPort;
    private final SchedulePersistencePort schedulePersistencePort;

    @Override
    @Transactional
    public Schedule execute(CreateScheduleCommand command, Integer scheduleVersionId) {
        ScheduleVersion scheduleVersion = getScheduleVersion(scheduleVersionId);
        Schedule schedule = scheduleMapper.toScheduleWithoutExternal(command);
        checkScheduleOverlapping(scheduleVersion, schedule);
        schedule.setScheduleVersion(scheduleVersion);
        Subject subject = subjectProviderPort.fetch(schedule.getSubject().getId());
        schedule.setSubject(subject);
        Teacher teacher = teacherProviderPort.fetch(schedule.getTeacher().getId());
        checkTeacherAvailability(teacher, schedule);
        schedule.setTeacher(teacher);
        Classroom classroom = classroomProviderPort.fetch(schedule.getClassroom().getId());
        checkClassroomAvailability(classroom.getId(), schedule);
        checkClassroomCapacityForYearbook(classroom, scheduleVersion.getYearbook());
        schedule.setClassroom(classroom);
        return schedulePersistencePort.save(schedule);
    }

    private ScheduleVersion getScheduleVersion(Integer scheduleVersionId) {
        ScheduleVersion scheduleVersion = scheduleVersionRepository.findScheduleVersionById(scheduleVersionId);
        scheduleVersionEnricher.enrich(scheduleVersion);
        return scheduleVersion;
    }

    private void checkScheduleOverlapping(ScheduleVersion scheduleVersion, Schedule schedule) {
        if(scheduleVersion.isOverlapped(schedule))
            throw new ScheduleAppointmentConflictException(
                    String.format("Schedule within ScheduleVersion (ID=%s) is already appointed on %s at %s - %s",
                            scheduleVersion.getId(),
                            schedule.getDayOfWeek(),
                            schedule.getStartTime(),
                            schedule.getEndTime())
            );
    }

    private void checkTeacherAvailability(Teacher teacher, Schedule schedule) {
        if(teacher.isAvailable(schedule.getDayOfWeek(), schedule.getStartTime(), schedule.getEndTime()))
            return;

        throw new ScheduleAppointmentConflictException(
                String.format("Teacher with ID '%s' is not available on %s at %s to %s",
                        teacher.getId(),
                        schedule.getDayOfWeek(),
                        schedule.getStartTime(),
                        schedule.getEndTime())
        );
    }

    private void checkClassroomAvailability(Integer classroomId, Schedule schedule) {
        List<Schedule> schedules = schedulePersistencePort.findByClassroomIdAndDayOfWeek(classroomId, schedule.getDayOfWeek());
        if(schedule.conflictsWithAny(schedules))
            throw new ScheduleAppointmentConflictException(
                    String.format("Classroom with ID '%s' is already occupied on %s between %s and %s",
                            classroomId,
                            schedule.getDayOfWeek(),
                            schedule.getStartTime(),
                            schedule.getEndTime())
            );
    }

    private void checkClassroomCapacityForYearbook(Classroom classroom, Yearbook yearbook) {
        if(classroom == null || yearbook == null)
            return;

        if(!classroom.canAccommodate(yearbook))
            throw new ScheduleAppointmentConflictException(
                    String.format("Classroom with ID '%s' cannot accomodate yearbook with ID '%s'",
                            classroom.getId(),
                            yearbook.getId())
            );
    }
}
