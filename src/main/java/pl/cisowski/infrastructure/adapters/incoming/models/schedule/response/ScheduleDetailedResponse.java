package pl.cisowski.infrastructure.adapters.incoming.models.schedule.response;

import lombok.Data;
import pl.cisowski.domain.model.schedule.ScheduleRecurrenceType;
import pl.cisowski.domain.model.schedule.ScheduleStatus;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Data
public class ScheduleDetailedResponse {
    private Integer id;
    private SubjectResponse subject;
    private TeacherResponse teacher;
    private ClassroomResponse classroom;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private ScheduleRecurrenceType recurrenceType;
    private ScheduleVersionResponse scheduleVersion;
    private ScheduleStatus status;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;

    public LocalTime getEndTime() {
        if(endTime != null)
            return endTime.truncatedTo(ChronoUnit.SECONDS);
        return null;
    }

    public LocalTime getStartTime() {
        if(startTime != null)
            return startTime.truncatedTo(ChronoUnit.SECONDS);
        return null;
    }
}
