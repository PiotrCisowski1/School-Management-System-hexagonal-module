package pl.cisowski.infrastructure.adapters.outgoing.external.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SubjectDto extends BaseExternalDto {
    private Integer id;
    private String name;
    private String code;
    private String description;
    private SubjectTypeDto subjectType;
}
