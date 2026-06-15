package pl.cisowski.infrastructure.adapters.outgoing.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import pl.cisowski.domain.model.Authority;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.models.AuthorityEntity;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    @Named("toAuthorities")
    Collection<Authority> toAuthorities(Collection<AuthorityEntity> authorityEntities);

    Authority toAuthority(AuthorityEntity authorityEntity);
}
