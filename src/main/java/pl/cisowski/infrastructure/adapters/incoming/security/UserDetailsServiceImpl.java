package pl.cisowski.infrastructure.adapters.incoming.security;

import pl.cisowski.domain.model.User;
import pl.cisowski.domain.ports.outgoing.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepositoryPort.findByEmail(email);
        return toUserDetails(user);
    }

    private UserDetails toUserDetails(User user) {
        return new pl.cisowski.infrastructure.adapters.outgoing.auth.UserDetails(user);
    }
}
