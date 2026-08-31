package pl.cisowski.domain.model.external;

public class ClassroomEquipment {
    private String equipmentName;
    private Integer quantity;

    public ClassroomEquipment(String equipmentName, Integer quantity) {
        this.equipmentName = equipmentName;
        this.quantity = quantity;
    }

    public ClassroomEquipment() {}

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
