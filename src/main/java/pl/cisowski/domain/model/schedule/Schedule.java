package pl.cisowski.domain.model.schedule;

import pl.cisowski.domain.model.external.Classroom;
import pl.cisowski.domain.model.external.Subject;
import pl.cisowski.domain.model.external.Teacher;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Objects;

public class Schedule {
    private Integer id;
    private ScheduleVersion scheduleVersion;
    private Subject subject;
    private Teacher teacher;
    private Classroom classroom;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private ScheduleRecurrenceType recurrenceType;
    private ScheduleStatus status;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;

    public Schedule() {}

    public Schedule(Integer id, ScheduleVersion scheduleVersion, Subject subject, Teacher teacher, Classroom classroom, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, ScheduleRecurrenceType recurrenceType, ScheduleStatus status, LocalDate effectiveDate, LocalDate expirationDate) {
        this.id = id;
        this.scheduleVersion = scheduleVersion;
        this.subject = subject;
        this.teacher = teacher;
        this.classroom = classroom;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurrenceType = recurrenceType;
        this.status = status;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
    }

    public Schedule(Schedule schedule, ScheduleVersion scheduleVersion) {
        this.scheduleVersion = scheduleVersion;
        this.subject = schedule.getSubject();
        this.teacher = schedule.getTeacher();
        this.classroom = schedule.getClassroom();
        this.dayOfWeek = schedule.getDayOfWeek();
        this.startTime = schedule.getStartTime();
        this.endTime = schedule.getEndTime();
        this.recurrenceType = schedule.getRecurrenceType();
        this.status = schedule.getStatus();
        this.effectiveDate = schedule.getEffectiveDate();
        this.expirationDate = schedule.getExpirationDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ScheduleVersion getScheduleVersion() {
        return scheduleVersion;
    }

    public void setScheduleVersion(ScheduleVersion scheduleVersion) {
        this.scheduleVersion = scheduleVersion;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
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

    public ScheduleStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduleStatus status) {
        this.status = status;
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

    public boolean isOverlapped(Schedule schedule) {
        if(schedule == null || Objects.equals(this.id, schedule.getId()) )
            return false;

        return hasDayAndTimeConflict(schedule) && hasDateOverlap(schedule);
    }

    private boolean hasDayAndTimeConflict(Schedule schedule) {
        return Objects.equals(this.dayOfWeek, schedule.getDayOfWeek())
                && this.startTime.isBefore(schedule.getEndTime())
                && this.endTime.isAfter(schedule.getStartTime());
    }

    private boolean hasDateOverlap(Schedule schedule) {
        LocalDate existingStart = this.effectiveDate;
        LocalDate existingEnd = this.expirationDate;
        LocalDate newStart = schedule.getEffectiveDate();
        LocalDate newEnd = schedule.getExpirationDate();

        boolean existingHasNoEnd = existingEnd == null;
        boolean newHasNoEnd = newEnd == null;

        return (existingHasNoEnd || !existingStart.isAfter(newEnd)) &&
                (newHasNoEnd || !newStart.isAfter(existingEnd));
    }

    public boolean conflictsWithAny(Collection<Schedule> conflictCandidates) {
        if(conflictCandidates == null)
            return false;

        return conflictCandidates.stream()
                .filter(Objects::nonNull)
                .anyMatch(this::isOverlapped);
    }
}
