package pl.cisowski.infrastructure.adapters.outgoing.external.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class YearbookDto extends BaseExternalDto {
    private Integer id;
    private String symbol;
    private TeacherDto headTeacher;
    private ZonedDateTime startingYear;
    private ZonedDateTime graduationYear;
}
