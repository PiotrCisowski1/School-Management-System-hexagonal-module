package pl.cisowski.infrastructure.adapters.outgoing.persistence.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cisowski.domain.model.schedule.ScheduleStatus;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;

@Data
@Entity
@Table(name = "schedule_version")
@NoArgsConstructor
public class ScheduleVersionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private ZonedDateTime createDate = ZonedDateTime.now();

    private boolean isActive = true;

    @Column(nullable = false)
    private Integer yearbookId;

    @OneToMany(mappedBy = "scheduleVersion", cascade = CascadeType.ALL)
    private Collection<ScheduleEntity> schedules = Collections.emptyList();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScheduleStatus status;
}
