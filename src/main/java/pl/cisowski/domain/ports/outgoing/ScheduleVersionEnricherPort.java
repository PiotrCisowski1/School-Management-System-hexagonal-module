package pl.cisowski.domain.ports.outgoing;

import pl.cisowski.domain.model.schedule.ScheduleVersion;

public interface ScheduleVersionEnricherPort {
    void enrich(ScheduleVersion scheduleVersion);
}
