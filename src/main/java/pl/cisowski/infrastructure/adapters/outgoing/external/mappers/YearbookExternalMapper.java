package pl.cisowski.infrastructure.adapters.outgoing.external.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;
import pl.cisowski.domain.model.external.Yearbook;
import pl.cisowski.infrastructure.adapters.outgoing.external.models.UserSummaryDto;
import pl.cisowski.infrastructure.adapters.outgoing.external.models.YearbookDto;

import java.util.Collection;

@Mapper(
        componentModel = "spring",
        uses = {TeacherExternalMapper.class})
public interface YearbookExternalMapper {

    @Mapping(target = "headTeacher", source = "headTeacher", qualifiedByName = "toTeacherFromYearbook")
    @Mapping(target = "studentsSize", source = "studentsInYearbook", qualifiedByName = "toStudentsSize")
    Yearbook toYearbook(YearbookDto yearbookDto);

    @Named("toStudentsSize")
    default Integer toStudentsSize(Collection<UserSummaryDto> studentsInYearbook) {
        if(CollectionUtils.isEmpty(studentsInYearbook))
            return null;

        return studentsInYearbook.size();
    }
}
