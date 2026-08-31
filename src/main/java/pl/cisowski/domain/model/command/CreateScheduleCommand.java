package pl.cisowski.domain.model.command;

import pl.cisowski.domain.model.schedule.ScheduleRecurrenceType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class CreateScheduleCommand {
    Integer subjectId;
    Integer teacherId;
    Integer classroomId;
    Integer dayOfWeek;
    LocalTime startTime;
    LocalTime endTime;
    ScheduleRecurrenceType recurrenceType;
    LocalDate effectiveDate;
    LocalDate expirationDate;

    public CreateScheduleCommand() {}

    public CreateScheduleCommand(Integer subjectId, Integer teacherId, Integer classroomId, Integer dayOfWeek, LocalTime startTime, LocalTime endTime, ScheduleRecurrenceType recurrenceType, LocalDate effectiveDate, LocalDate expirationDate) {
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.classroomId = classroomId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurrenceType = recurrenceType;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        if(startTime != null)
            return startTime.truncatedTo(ChronoUnit.SECONDS);
        return null;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        if(endTime != null)
            return endTime.truncatedTo(ChronoUnit.SECONDS);
        return null;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public ScheduleRecurrenceType getRecurrenceType() {
        return recurrenceType;
    }

    public void setRecurrenceType(ScheduleRecurrenceType recurrenceType) {
        this.recurrenceType = recurrenceType;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
