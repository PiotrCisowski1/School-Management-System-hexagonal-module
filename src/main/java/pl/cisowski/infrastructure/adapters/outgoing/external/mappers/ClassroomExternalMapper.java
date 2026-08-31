package pl.cisowski.infrastructure.adapters.outgoing.external.mappers;

import org.mapstruct.Mapper;
import pl.cisowski.domain.model.external.Classroom;
import pl.cisowski.infrastructure.adapters.outgoing.external.models.ClassroomDto;

@Mapper(componentModel = "spring")
public interface ClassroomExternalMapper {

    Classroom toClassroom(ClassroomDto dto);
}
