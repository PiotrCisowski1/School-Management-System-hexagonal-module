package pl.cisowski.infrastructure.adapters.outgoing.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.models.ScheduleEntity;

import java.time.DayOfWeek;
import java.util.List;

public interface JpaScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
    List<ScheduleEntity> findByClassroomIdAndDayOfWeek(Integer classroomId, DayOfWeek dayOfWeek);
}
