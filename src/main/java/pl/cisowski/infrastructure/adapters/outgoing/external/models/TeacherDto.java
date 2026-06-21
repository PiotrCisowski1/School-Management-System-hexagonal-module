package pl.cisowski.infrastructure.adapters.outgoing.external.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherDto extends BaseExternalDto {
    private Integer id;
    private YearbookDto leadingYearbook;
    private Collection<SubjectDto> teachingSubjects;
    private Collection<TeacherAvailabilityDto> availability;
}
