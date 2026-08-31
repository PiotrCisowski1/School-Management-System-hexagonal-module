package pl.cisowski.domain.ports.incoming;

import pl.cisowski.domain.model.user.User;

public interface UserAuthPort {
    User authenticate(String email, String password);
}
