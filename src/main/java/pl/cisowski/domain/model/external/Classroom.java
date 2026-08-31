package pl.cisowski.domain.model.external;

import java.util.Collection;

public class Classroom {
    private Integer id;
    private String name;
    private Integer capacity;
    private Collection<ClassroomEquipment> classroomEquipments;

    public Classroom(Integer id, String name, Integer capacity, Collection<ClassroomEquipment> classroomEquipments) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.classroomEquipments = classroomEquipments;
    }

    public Classroom() {}

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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Collection<ClassroomEquipment> getClassroomEquipments() {
        return classroomEquipments;
    }

    public void setClassroomEquipments(Collection<ClassroomEquipment> classroomEquipments) {
        this.classroomEquipments = classroomEquipments;
    }

    public boolean canAccommodate(Yearbook yearbook) {
        if (yearbook == null || yearbook.getStudentsSize() == null || this.capacity == null) {
            return true;
        }
        return this.capacity >= yearbook.getStudentsSize();
    }
}
