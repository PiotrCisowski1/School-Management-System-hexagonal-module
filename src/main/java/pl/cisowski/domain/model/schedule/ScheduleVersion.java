package pl.cisowski.domain.model.schedule;

import pl.cisowski.domain.model.external.Yearbook;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ScheduleVersion {
    private Integer id;
    private String name;
    private ZonedDateTime createDate;
    private boolean isActive;
    private Yearbook yearbook;
    private Collection<Schedule> schedules;
    private ScheduleStatus status;

    public ScheduleVersion() {}

    public ScheduleVersion(Integer id, String name, ZonedDateTime createDate, boolean isActive, Yearbook yearbook, Collection<Schedule> schedules, ScheduleStatus scheduleStatus) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.isActive = isActive;
        this.yearbook = yearbook;
        this.schedules = schedules;
        this.status = scheduleStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Yearbook getYearbook() {
        return yearbook;
    }

    public void setYearbook(Yearbook yearbook) {
        this.yearbook = yearbook;
    }

    public Collection<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Collection<Schedule> schedules) {
        this.schedules = schedules;
    }

    public ScheduleStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduleStatus status) {
        this.status = status;
    }

    public ScheduleVersion(ScheduleVersion scheduleVersion) {
        if(scheduleVersion == null)
            return;

        List<Schedule> clonedSchedules = new ArrayList<>();
        if(scheduleVersion.schedules != null)
            scheduleVersion.schedules.forEach(schedule -> {
               clonedSchedules.add(new Schedule(schedule, this));
            });
        this.name = scheduleVersion.name != null ? scheduleVersion.name.concat(" (copy)") : "Copy";
        this.createDate = ZonedDateTime.now();
        this.isActive = false;
        this.yearbook = scheduleVersion.yearbook;
        this.schedules = clonedSchedules;
        this.status = ScheduleStatus.SCHEDULED;
    }

    public boolean isOverlapped(Schedule schedule) {
        if(schedule == null || schedules == null)
            return false;

        return schedules.stream()
                .filter(Objects::nonNull)
                .anyMatch(existingSchedule -> existingSchedule.isOverlapped(schedule));
    }
}
