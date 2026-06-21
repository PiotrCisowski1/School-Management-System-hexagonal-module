package pl.cisowski.infrastructure.adapters.outgoing.persistence;

import org.mapstruct.Mapping;
import pl.cisowski.domain.model.user.User;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.models.UserEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {AuthorityMapper.class})
public interface UserMapper {

    @Mapping(target = "enabled", source = "isEnabled")
    @Mapping(target = "authorities", source = "authority", qualifiedByName = "toAuthorities")
    User toUser(UserEntity userEntity);
}
