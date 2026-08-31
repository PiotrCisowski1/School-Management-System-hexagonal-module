package pl.cisowski.domain.model.external;

import pl.cisowski.domain.model.user.User;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Objects;

public class Teacher extends User {
    private Integer id;
    private Yearbook leadingYearbook;
    private Collection<Subject> teachingSubjects;
    private Collection<TeacherAvailability> availability;

    public Teacher() {}

    public Teacher(Integer id, Yearbook leadingYearbook, Collection<Subject> teachingSubjects, Collection<TeacherAvailability> availability) {
        this.id = id;
        this.leadingYearbook = leadingYearbook;
        this.teachingSubjects = teachingSubjects;
        this.availability = availability;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Yearbook getLeadingYearbook() {
        return leadingYearbook;
    }

    public void setLeadingYearbook(Yearbook leadingYearbook) {
        this.leadingYearbook = leadingYearbook;
    }

    public Collection<Subject> getTeachingSubjects() {
        return teachingSubjects;
    }

    public void setTeachingSubjects(Collection<Subject> teachingSubjects) {
        this.teachingSubjects = teachingSubjects;
    }

    public Collection<TeacherAvailability> getAvailability() {
        return availability;
    }

    public void setAvailability(Collection<TeacherAvailability> availability) {
        this.availability = availability;
    }

    public boolean isAvailable(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        if(availability != null) {
            return availability.stream()
                    .filter(Objects::nonNull)
                    .anyMatch(availability -> availability.isAvailableInGivenTimeFrame(dayOfWeek, startTime, endTime));
        }

        return false;
    }

}
