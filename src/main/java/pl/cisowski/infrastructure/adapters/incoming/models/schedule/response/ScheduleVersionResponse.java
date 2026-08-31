package pl.cisowski.infrastructure.adapters.incoming.models.schedule.response;

import lombok.Data;

@Data
public class ScheduleVersionResponse {
    private Integer id;
    private String name;
    private boolean isActive;
}
