package pl.cisowski.domain.ports.outgoing.providers;

import pl.cisowski.domain.model.external.Teacher;

public interface TeacherProviderPort {
    Teacher fetch(Integer teacherId);
}
