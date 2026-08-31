package pl.cisowski.infrastructure.adapters.outgoing.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.models.ScheduleVersionEntity;

public interface JpaScheduleVersionRepository extends JpaRepository<ScheduleVersionEntity, Integer> {
}
