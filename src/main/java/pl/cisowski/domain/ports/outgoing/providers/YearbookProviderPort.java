package pl.cisowski.domain.ports.outgoing.providers;

import pl.cisowski.domain.model.external.Yearbook;

public interface YearbookProviderPort {
    Yearbook fetchYearbook(Integer yearbookId);
}
