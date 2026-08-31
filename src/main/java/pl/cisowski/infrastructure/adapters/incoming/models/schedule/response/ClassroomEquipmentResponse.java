package pl.cisowski.infrastructure.adapters.incoming.models.schedule.response;

import lombok.Data;

@Data
public class ClassroomEquipmentResponse {
    private String equipmentName;
    private Integer quantity;
}
