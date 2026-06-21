package pl.cisowski.infrastructure.adapters.outgoing.external.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.DayOfWeek;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherAvailabilityDto extends BaseExternalDto {
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isAvailable;
}
