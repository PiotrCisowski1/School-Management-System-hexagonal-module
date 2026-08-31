package application.services;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.cisowski.application.mappers.ScheduleMapper;
import pl.cisowski.application.services.CreateScheduleService;
import pl.cisowski.domain.exceptions.ScheduleAppointmentConflictException;
import pl.cisowski.domain.model.command.CreateScheduleCommand;
import pl.cisowski.domain.model.external.Classroom;
import pl.cisowski.domain.model.external.Subject;
import pl.cisowski.domain.model.external.Teacher;
import pl.cisowski.domain.model.external.Yearbook;
import pl.cisowski.domain.model.schedule.Schedule;
import pl.cisowski.domain.model.schedule.ScheduleVersion;
import pl.cisowski.domain.ports.outgoing.SchedulePersistencePort;
import pl.cisowski.domain.ports.outgoing.ScheduleVersionEnricherPort;
import pl.cisowski.domain.ports.outgoing.ScheduleVersionRepositoryPort;
import pl.cisowski.domain.ports.outgoing.providers.ClassroomProviderPort;
import pl.cisowski.domain.ports.outgoing.providers.SubjectProviderPort;
import pl.cisowski.domain.ports.outgoing.providers.TeacherProviderPort;

import java.time.DayOfWeek;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateScheduleServiceTest {

    @Mock
    private ScheduleVersionRepositoryPort scheduleVersionRepository;

    @Mock
    private ScheduleVersionEnricherPort scheduleVersionEnricher;

    @Mock
    private ScheduleMapper scheduleMapper;

    @Mock
    private SubjectProviderPort subjectProviderPort;

    @Mock
    private TeacherProviderPort teacherProviderPort;

    @Mock
    private ClassroomProviderPort classroomProviderPort;

    @Mock
    private SchedulePersistencePort schedulePersistencePort;

    @InjectMocks
    private CreateScheduleService createScheduleService;

    @Test
    void shouldCreateAndSaveScheduleSuccessfully() {
        Integer versionId = 1;
        CreateScheduleCommand command = Instancio.create(CreateScheduleCommand.class);

        ScheduleVersion scheduleVersion = mock(ScheduleVersion.class);
        Schedule schedule = mock(Schedule.class);
        Subject subject = Instancio.create(Subject.class);
        Teacher teacher = mock(Teacher.class);
        Classroom classroom = mock(Classroom.class);
        Yearbook yearbook = mock(Yearbook.class);
        Schedule savedSchedule = mock(Schedule.class);

        when(scheduleVersionRepository.findScheduleVersionById(versionId)).thenReturn(scheduleVersion);
        when(scheduleMapper.toScheduleWithoutExternal(command)).thenReturn(schedule);
        when(scheduleVersion.isOverlapped(schedule)).thenReturn(false);

        when(schedule.getSubject()).thenReturn(subject);
        when(subjectProviderPort.fetch(subject.getId())).thenReturn(subject);

        when(schedule.getTeacher()).thenReturn(teacher);
        when(teacher.getId()).thenReturn(10);
        when(teacherProviderPort.fetch(10)).thenReturn(teacher);
        when(teacher.isAvailable(any(), any(), any())).thenReturn(true);

        when(schedule.getClassroom()).thenReturn(classroom);
        when(classroom.getId()).thenReturn(20);
        when(classroomProviderPort.fetch(20)).thenReturn(classroom);

        when(schedule.getDayOfWeek()).thenReturn(DayOfWeek.MONDAY);
        when(schedulePersistencePort.findByClassroomIdAndDayOfWeek(20, DayOfWeek.MONDAY)).thenReturn(List.of());
        when(schedule.conflictsWithAny(anyList())).thenReturn(false);

        when(scheduleVersion.getYearbook()).thenReturn(yearbook);
        when(classroom.getCapacity()).thenReturn(30);
        when(yearbook.getStudentsSize()).thenReturn(25);

        when(schedulePersistencePort.save(schedule)).thenReturn(savedSchedule);

        Schedule result = createScheduleService.execute(command, versionId);

        assertThat(result).isNotNull().isEqualTo(savedSchedule);

        verify(scheduleVersionEnricher).enrich(scheduleVersion);
        verify(schedule).setScheduleVersion(scheduleVersion);
        verify(schedule).setSubject(subject);
        verify(schedule).setTeacher(teacher);
        verify(schedule).setClassroom(classroom);
        verify(schedulePersistencePort).save(schedule);
    }

    @Test
    void shouldThrowExceptionWhenScheduleOverlapsInVersion() {
        Integer versionId = 1;
        CreateScheduleCommand command = Instancio.create(CreateScheduleCommand.class);

        ScheduleVersion scheduleVersion = mock(ScheduleVersion.class);
        Schedule schedule = mock(Schedule.class);

        when(scheduleVersionRepository.findScheduleVersionById(versionId)).thenReturn(scheduleVersion);
        when(scheduleMapper.toScheduleWithoutExternal(command)).thenReturn(schedule);
        when(scheduleVersion.isOverlapped(schedule)).thenReturn(true);

        assertThatThrownBy(() -> createScheduleService.execute(command, versionId))
                .isInstanceOf(ScheduleAppointmentConflictException.class);

        verify(schedulePersistencePort, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenTeacherIsNotAvailable() {
        Integer versionId = 1;
        CreateScheduleCommand command = Instancio.create(CreateScheduleCommand.class);

        ScheduleVersion scheduleVersion = mock(ScheduleVersion.class);
        Schedule schedule = mock(Schedule.class);
        Subject subject = Instancio.create(Subject.class);
        Teacher teacher = mock(Teacher.class);

        when(scheduleVersionRepository.findScheduleVersionById(versionId)).thenReturn(scheduleVersion);
        when(scheduleMapper.toScheduleWithoutExternal(command)).thenReturn(schedule);
        when(scheduleVersion.isOverlapped(schedule)).thenReturn(false);

        when(schedule.getSubject()).thenReturn(subject);
        when(subjectProviderPort.fetch(subject.getId())).thenReturn(subject);

        when(schedule.getTeacher()).thenReturn(teacher);
        when(teacher.getId()).thenReturn(10);
        when(teacherProviderPort.fetch(10)).thenReturn(teacher);
        when(teacher.isAvailable(any(), any(), any())).thenReturn(false);

        assertThatThrownBy(() -> createScheduleService.execute(command, versionId))
                .isInstanceOf(ScheduleAppointmentConflictException.class);

        verify(schedulePersistencePort, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenClassroomHasConflict() {
        Integer versionId = 1;
        CreateScheduleCommand command = Instancio.create(CreateScheduleCommand.class);

        ScheduleVersion scheduleVersion = mock(ScheduleVersion.class);
        Schedule schedule = mock(Schedule.class);
        Subject subject = Instancio.create(Subject.class);
        Teacher teacher = mock(Teacher.class);
        Classroom classroom = mock(Classroom.class);

        when(scheduleVersionRepository.findScheduleVersionById(versionId)).thenReturn(scheduleVersion);
        when(scheduleMapper.toScheduleWithoutExternal(command)).thenReturn(schedule);
        when(scheduleVersion.isOverlapped(schedule)).thenReturn(false);

        when(schedule.getSubject()).thenReturn(subject);
        when(subjectProviderPort.fetch(subject.getId())).thenReturn(subject);

        when(schedule.getTeacher()).thenReturn(teacher);
        when(teacher.getId()).thenReturn(10);
        when(teacherProviderPort.fetch(10)).thenReturn(teacher);
        when(teacher.isAvailable(any(), any(), any())).thenReturn(true);

        when(schedule.getClassroom()).thenReturn(classroom);
        when(classroom.getId()).thenReturn(20);
        when(classroomProviderPort.fetch(20)).thenReturn(classroom);

        when(schedule.getDayOfWeek()).thenReturn(DayOfWeek.MONDAY);
        when(schedulePersistencePort.findByClassroomIdAndDayOfWeek(20, DayOfWeek.MONDAY)).thenReturn(List.of(mock(Schedule.class)));
        when(schedule.conflictsWithAny(anyList())).thenReturn(true);

        assertThatThrownBy(() -> createScheduleService.execute(command, versionId))
                .isInstanceOf(ScheduleAppointmentConflictException.class);

        verify(schedulePersistencePort, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenClassroomCapacityIsInsufficient() {
        Integer versionId = 1;
        CreateScheduleCommand command = Instancio.create(CreateScheduleCommand.class);

        ScheduleVersion scheduleVersion = mock(ScheduleVersion.class);
        Schedule schedule = mock(Schedule.class);
        Subject subject = Instancio.create(Subject.class);
        Teacher teacher = mock(Teacher.class);
        Classroom classroom = mock(Classroom.class);
        Yearbook yearbook = mock(Yearbook.class);

        when(scheduleVersionRepository.findScheduleVersionById(versionId)).thenReturn(scheduleVersion);
        when(scheduleMapper.toScheduleWithoutExternal(command)).thenReturn(schedule);
        when(scheduleVersion.isOverlapped(schedule)).thenReturn(false);

        when(schedule.getSubject()).thenReturn(subject);
        when(subjectProviderPort.fetch(subject.getId())).thenReturn(subject);

        when(schedule.getTeacher()).thenReturn(teacher);
        when(teacher.getId()).thenReturn(10);
        when(teacherProviderPort.fetch(10)).thenReturn(teacher);
        when(teacher.isAvailable(any(), any(), any())).thenReturn(true);

        when(schedule.getClassroom()).thenReturn(classroom);
        when(classroom.getId()).thenReturn(20);
        when(classroomProviderPort.fetch(20)).thenReturn(classroom);

        when(schedule.getDayOfWeek()).thenReturn(DayOfWeek.MONDAY);
        when(schedulePersistencePort.findByClassroomIdAndDayOfWeek(20, DayOfWeek.MONDAY)).thenReturn(List.of());
        when(schedule.conflictsWithAny(anyList())).thenReturn(false);

        when(scheduleVersion.getYearbook()).thenReturn(yearbook);
        when(classroom.getCapacity()).thenReturn(15);
        when(yearbook.getStudentsSize()).thenReturn(30);

        assertThatThrownBy(() -> createScheduleService.execute(command, versionId))
                .isInstanceOf(ScheduleAppointmentConflictException.class);

        verify(schedulePersistencePort, never()).save(any());
    }
}
