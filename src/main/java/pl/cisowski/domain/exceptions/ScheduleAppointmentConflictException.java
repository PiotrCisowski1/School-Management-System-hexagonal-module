package pl.cisowski.domain.exceptions;

public class ScheduleAppointmentConflictException extends RuntimeException {
    public ScheduleAppointmentConflictException(String message) {
        super(message);
    }
}
