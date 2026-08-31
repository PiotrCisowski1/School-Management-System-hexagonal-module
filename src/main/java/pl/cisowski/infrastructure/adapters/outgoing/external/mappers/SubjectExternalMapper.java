    package pl.cisowski.infrastructure.adapters.outgoing.external.mappers;

    import org.mapstruct.IterableMapping;
    import org.mapstruct.Mapper;
    import org.mapstruct.Mapping;
    import org.mapstruct.Named;
    import pl.cisowski.domain.model.external.Subject;
    import pl.cisowski.infrastructure.adapters.outgoing.external.models.SubjectDto;
    import pl.cisowski.infrastructure.adapters.outgoing.external.models.SubjectTypeDto;

    import java.util.Collection;

    @Mapper(componentModel = "spring")
    public interface SubjectExternalMapper {

        @Named("toSubjectList")
        Collection<Subject> toSubjects(Collection<SubjectDto> dtos);

        @Mapping(target = "subjectTypeName", source = "subjectType.name")
        Subject toSubject(SubjectDto dto);
    }
