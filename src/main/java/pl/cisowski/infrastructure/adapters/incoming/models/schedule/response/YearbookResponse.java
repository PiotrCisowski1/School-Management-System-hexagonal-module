package pl.cisowski.infrastructure.adapters.incoming.models.schedule.response;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class YearbookResponse {
    private Integer id;
    private String symbol;
    private TeacherResponse headTeacher;
    private ZonedDateTime startingYear;
    private ZonedDateTime graduationYear;
}
