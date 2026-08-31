package pl.cisowski.infrastructure.adapters.outgoing.external.enrichers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.cisowski.domain.model.external.Yearbook;
import pl.cisowski.domain.model.schedule.ScheduleVersion;
import pl.cisowski.domain.ports.outgoing.ScheduleVersionEnricherPort;
import pl.cisowski.domain.ports.outgoing.providers.YearbookProviderPort;

@Component
@RequiredArgsConstructor
public class ScheduleVersionEnricherAdapter implements ScheduleVersionEnricherPort {

    private final YearbookProviderPort yearbookProvider;

    public void enrich(ScheduleVersion scheduleVersion) {
        if(scheduleVersion == null || scheduleVersion.getYearbook() == null)
            return;

        Yearbook yearbook = yearbookProvider.fetchYearbook(scheduleVersion.getYearbook().getId());
        scheduleVersion.setYearbook(yearbook);
    }
}
