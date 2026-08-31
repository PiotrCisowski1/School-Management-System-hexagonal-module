package pl.cisowski.infrastructure.adapters.outgoing.external.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import pl.cisowski.domain.model.external.TeacherAvailability;
import pl.cisowski.infrastructure.adapters.outgoing.external.models.TeacherAvailabilityDto;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface TeacherAvailabilityExternalMapper {

    @Named("toTeacherAvailabilityList")
    Collection<TeacherAvailability> toTeacherAvailabilityList(Collection<TeacherAvailabilityDto> dtos);

    TeacherAvailability toTeacherAvailability(TeacherAvailabilityDto dto);
}
