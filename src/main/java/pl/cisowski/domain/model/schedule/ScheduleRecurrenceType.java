package pl.cisowski.domain.model.schedule;

import java.util.List;

public enum ScheduleRecurrenceType {
    NONE,
    WEEKLY,
    BIWEEKLY,
    MONTHLY;

    public static List<ScheduleRecurrenceType> getRegularRecurrenceTypes() {
        return List.of(WEEKLY, BIWEEKLY, MONTHLY);
    }
}
