package pl.cisowski.domain.ports.outgoing;

import pl.cisowski.domain.model.User;

public interface UserRepositoryPort {
    User findByEmail(String email);
}
