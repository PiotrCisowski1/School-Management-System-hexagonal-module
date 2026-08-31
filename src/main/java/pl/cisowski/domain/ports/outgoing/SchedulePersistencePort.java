package pl.cisowski.domain.ports.outgoing;

import pl.cisowski.domain.model.schedule.Schedule;

import java.time.DayOfWeek;
import java.util.List;

public interface SchedulePersistencePort {
    List<Schedule> findByClassroomIdAndDayOfWeek(Integer classroomId, DayOfWeek dayOfWeek);
    Schedule save(Schedule schedule);
}
