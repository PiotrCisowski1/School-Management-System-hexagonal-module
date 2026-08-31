package pl.cisowski.domain.ports.outgoing.providers;

import pl.cisowski.domain.model.external.Classroom;

public interface ClassroomProviderPort {
    Classroom fetch(Integer classroomId);
}
