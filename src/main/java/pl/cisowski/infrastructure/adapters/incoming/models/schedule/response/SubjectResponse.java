package pl.cisowski.infrastructure.adapters.incoming.models.schedule.response;

import lombok.Data;

@Data
public class SubjectResponse {
    private Integer id;
    private String name;
    private String code;
    private String description;
    private String subjectTypeName;
}
