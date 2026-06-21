package pl.cisowski.infrastructure.adapters.outgoing.external.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClassroomEquipmentDto extends BaseExternalDto {
    private EquipmentDto equipment;
    private Integer quantity;
}
