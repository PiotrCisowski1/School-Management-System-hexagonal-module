package pl.cisowski.infrastructure.adapters.outgoing.persistence.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cisowski.domain.model.schedule.ScheduleRecurrenceType;
import pl.cisowski.domain.model.schedule.ScheduleStatus;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Data
@Entity
@Table(name = "schedules")
@NoArgsConstructor
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "schedule_version_id", nullable = false)
    private ScheduleVersionEntity scheduleVersion;

    @Column(nullable = false)
    private Integer subjectId;

    @Column(nullable = false)
    private Integer teacherId;

    @Column(nullable = false)
    private Integer classroomId;

    private DayOfWeek dayOfWeek;

    @Column(columnDefinition = "TIME")
    private LocalTime startTime;

    @Column(columnDefinition = "TIME")
    private LocalTime endTime;

    private ScheduleRecurrenceType recurrenceType = ScheduleRecurrenceType.NONE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScheduleStatus status = ScheduleStatus.SCHEDULED;

    @Column(nullable = false)
    private LocalDate effectiveDate;

    private LocalDate expirationDate;

    public ScheduleEntity(ScheduleEntity entity, ScheduleVersionEntity scheduleVersion) {
        this.scheduleVersion = scheduleVersion;
        this.subjectId = entity.getSubjectId();
        this.teacherId = entity.getTeacherId();
        this.classroomId = entity.getClassroomId();
        this.dayOfWeek = entity.getDayOfWeek();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        this.recurrenceType = entity.getRecurrenceType();
    }

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
