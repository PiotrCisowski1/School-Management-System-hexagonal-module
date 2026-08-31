package pl.cisowski.domain.ports.outgoing.providers;

import pl.cisowski.domain.model.external.Subject;

public interface SubjectProviderPort {
    Subject fetch(Integer subjectId);
}
