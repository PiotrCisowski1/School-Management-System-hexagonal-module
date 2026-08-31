package pl.cisowski.infrastructure.adapters.outgoing.persistence.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.cisowski.domain.model.schedule.Schedule;
import pl.cisowski.domain.ports.outgoing.SchedulePersistencePort;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.mapper.ScheduleMapper;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.models.ScheduleEntity;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.repositories.JpaScheduleRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SchedulePersistenceRepositoryAdapter implements SchedulePersistencePort {

    private final JpaScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public List<Schedule> findByClassroomIdAndDayOfWeek(Integer classroomId, DayOfWeek dayOfWeek) {
        List<ScheduleEntity> schedules = scheduleRepository.findByClassroomIdAndDayOfWeek(classroomId, dayOfWeek);
        return scheduleMapper.toSchedules(schedules);
    }

    @Override
    public Schedule save(Schedule schedule) {
        if(schedule == null)
            return new Schedule();

        ScheduleEntity scheduleEntity = scheduleMapper.toScheduleEntity(schedule);
        ScheduleEntity saved = scheduleRepository.save(scheduleEntity);
        return scheduleMapper.toSchedule(saved);
    }
}
