package pl.cisowski.application.services;

import pl.cisowski.domain.model.user.User;
import pl.cisowski.domain.ports.incoming.UserAuthPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.cisowski.infrastructure.adapters.outgoing.auth.UserDetails;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserAuthPort {

    private final AuthenticationManager authenticationManager;

    @Override
    public User authenticate(String email, String password) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        return  ((UserDetails) auth.getPrincipal()).getUser();
    }
}
