package pl.cisowski.infrastructure.adapters.outgoing.external.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.cisowski.domain.model.external.Teacher;
import pl.cisowski.infrastructure.adapters.outgoing.external.models.TeacherDto;

@Mapper(componentModel = "spring", uses = {SubjectExternalMapper.class, TeacherAvailabilityExternalMapper.class})
public interface TeacherExternalMapper {

    @Named("toTeacherFromYearbook")
    @Mapping(target = "leadingYearbook", ignore = true)
    @Mapping(target = "teachingSubjects", source = "teachingSubjects", qualifiedByName = "toSubjectList")
    @Mapping(target = "availability", source = "availability", qualifiedByName = "toTeacherAvailabilityList")
    Teacher toTeacher(TeacherDto dto);
}
