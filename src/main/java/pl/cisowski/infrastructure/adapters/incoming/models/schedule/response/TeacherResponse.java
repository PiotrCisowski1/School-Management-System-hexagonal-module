package pl.cisowski.infrastructure.adapters.incoming.models.schedule.response;

import lombok.Data;


import java.util.Collection;

@Data
public class TeacherResponse {
    private Integer id;
    private YearbookResponse leadingYearbook;
    private Collection<SubjectResponse> teachingSubjects;
    private Collection<TeacherAvailabilityResponse> availability;
}
