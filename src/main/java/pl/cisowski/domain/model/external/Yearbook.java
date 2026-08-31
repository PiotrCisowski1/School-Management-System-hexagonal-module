package pl.cisowski.domain.model.external;

import java.time.ZonedDateTime;

public class Yearbook {
    private Integer id;
    private String symbol;
    private Teacher headTeacher;
    private ZonedDateTime startingYear;
    private ZonedDateTime graduationYear;
    private Integer studentsSize;

    public Yearbook() {}

    public Yearbook(Integer id, String symbol, Teacher headTeacher, ZonedDateTime startingYear, ZonedDateTime graduationYear, Integer studentsSize) {
        this.id = id;
        this.symbol = symbol;
        this.headTeacher = headTeacher;
        this.startingYear = startingYear;
        this.graduationYear = graduationYear;
        this.studentsSize = studentsSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Teacher getHeadTeacher() {
        return headTeacher;
    }

    public void setHeadTeacher(Teacher headTeacher) {
        this.headTeacher = headTeacher;
    }

    public ZonedDateTime getStartingYear() {
        return startingYear;
    }

    public void setStartingYear(ZonedDateTime startingYear) {
        this.startingYear = startingYear;
    }

    public ZonedDateTime getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(ZonedDateTime graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Integer getStudentsSize() {
        return studentsSize;
    }

    public void setStudentsSize(Integer studentsSize) {
        this.studentsSize = studentsSize;
    }
}
