package pl.cisowski.infrastructure.adapters.outgoing.external.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClassroomDto extends BaseExternalDto {
    private Integer id;
    private String name;
    private Integer capacity;
    private Collection<ClassroomEquipmentDto> classroomEquipments;
}
