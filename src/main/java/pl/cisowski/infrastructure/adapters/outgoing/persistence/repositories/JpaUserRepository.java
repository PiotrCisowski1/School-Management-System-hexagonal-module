package pl.cisowski.infrastructure.adapters.outgoing.persistence.repositories;

import pl.cisowski.infrastructure.adapters.outgoing.persistence.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
