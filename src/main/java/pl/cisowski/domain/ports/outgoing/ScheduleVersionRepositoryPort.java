package pl.cisowski.domain.ports.outgoing;

import pl.cisowski.domain.model.schedule.ScheduleVersion;

public interface ScheduleVersionRepositoryPort {
    ScheduleVersion findScheduleVersionById(Integer scheduleVersionId);
}
