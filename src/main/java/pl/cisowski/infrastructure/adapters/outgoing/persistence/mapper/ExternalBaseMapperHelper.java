package pl.cisowski.infrastructure.adapters.outgoing.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.cisowski.domain.model.external.Classroom;
import pl.cisowski.domain.model.external.Subject;
import pl.cisowski.domain.model.external.Teacher;

@Mapper(componentModel = "spring")
public interface ExternalBaseMapperHelper {

    @Named("toBaseSubject")
    @Mapping(target = "id", source = "subjectId")
    Subject toBaseSubject(Integer subjectId);

    @Named("toBaseClassroom")
    @Mapping(target = "id", source = "classroomId")
    Classroom toBaseClassroom(Integer classroomId);

    @Named("toBaseTeacher")
    @Mapping(target = "id", source = "teacherId")
    Teacher toBaseTeacher(Integer teacherId);
}
