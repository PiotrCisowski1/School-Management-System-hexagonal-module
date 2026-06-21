package pl.cisowski.domain.ports.outgoing;

import pl.cisowski.domain.model.user.User;

public interface UserAuthPort {
    User authenticate(String email, String password);
}
