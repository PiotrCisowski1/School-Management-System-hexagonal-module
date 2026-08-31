package pl.cisowski.infrastructure.adapters.outgoing.persistence.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.cisowski.domain.exceptions.EntityNotFoundException;
import pl.cisowski.domain.model.schedule.ScheduleVersion;
import pl.cisowski.domain.ports.outgoing.ScheduleVersionRepositoryPort;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.mapper.ScheduleVersionMapper;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.models.ScheduleVersionEntity;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.repositories.JpaScheduleVersionRepository;


@Repository
@RequiredArgsConstructor
public class ScheduleVersionRepositoryAdapter implements ScheduleVersionRepositoryPort {

    private final JpaScheduleVersionRepository scheduleVersionRepository;
    private final ScheduleVersionMapper scheduleVersionMapper;

    @Override
    public ScheduleVersion findScheduleVersionById(Integer scheduleVersionId) {
        ScheduleVersionEntity scheduleVersion = scheduleVersionRepository.findById(scheduleVersionId)
                .orElseThrow(() -> new EntityNotFoundException("ScheduleVersion", scheduleVersionId.toString()));
        return scheduleVersionMapper.toScheduleVersion(scheduleVersion);
    }
}
