package pl.cisowski.infrastructure.adapters.incoming.models.schedule.response;

import lombok.Data;

import java.util.Collection;

@Data
public class ClassroomResponse {
    private Integer id;
    private String name;
    private Integer capacity;
    private Collection<ClassroomEquipmentResponse> classroomEquipments;
}
