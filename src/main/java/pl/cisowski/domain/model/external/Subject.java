package pl.cisowski.domain.model.external;

public class Subject {
    private Integer id;
    private String name;
    private String code;
    private String description;
    private String subjectTypeName;

    public Subject() {}

    public Subject(Integer id, String name, String code, String description, String subjectTypeName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.subjectTypeName = subjectTypeName;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubjectTypeName() {
        return subjectTypeName;
    }

    public void setSubjectTypeName(String subjectTypeName) {
        this.subjectTypeName = subjectTypeName;
    }
}
