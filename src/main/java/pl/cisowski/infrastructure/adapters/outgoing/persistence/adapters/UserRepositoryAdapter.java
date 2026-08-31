package pl.cisowski.infrastructure.adapters.outgoing.persistence.adapters;

import pl.cisowski.domain.exceptions.UserNotFoundException;
import pl.cisowski.domain.model.user.User;
import pl.cisowski.domain.ports.outgoing.UserRepositoryPort;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.mapper.UserMapper;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.models.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.repositories.JpaUserRepository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        return userMapper.toUser(userEntity);
    }
}
