package pl.cisowski.domain.model.external;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TeacherAvailability {
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isAvailable;

    public TeacherAvailability() {}

    public TeacherAvailability(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, boolean isAvailable) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = isAvailable;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isAvailableInGivenTimeFrame(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        if(dayOfWeek == null || startTime == null || endTime == null)
            return false;
        return this.dayOfWeek.equals(dayOfWeek) &&
                (this.startTime.isBefore(startTime) || this.startTime.equals(startTime)) &&
                (this.endTime.isAfter(endTime) || this.endTime.equals(endTime)) &&
                this.isAvailable;
    }
}
