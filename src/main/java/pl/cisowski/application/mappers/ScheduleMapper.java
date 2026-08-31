package pl.cisowski.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.cisowski.domain.model.command.CreateScheduleCommand;
import pl.cisowski.domain.model.external.Classroom;
import pl.cisowski.domain.model.external.Subject;
import pl.cisowski.domain.model.external.Teacher;
import pl.cisowski.domain.model.schedule.Schedule;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    @Mapping(target = "subject", source = "subjectId", qualifiedByName = "mapSubjectWithId")
    @Mapping(target = "classroom", source = "classroomId", qualifiedByName = "mapClassroomWithId")
    @Mapping(target = "teacher", source = "teacherId", qualifiedByName = "mapTeacherWithId")
    @Mapping(target = "status", constant = "SCHEDULED")
    Schedule toScheduleWithoutExternal(CreateScheduleCommand command);

    @Named("mapSubjectWithId")
    @Mapping(target = "id", source = "subjectId")
    Subject mapSubjectWithId(Integer subjectId);

    @Named("mapTeacherWithId")
    @Mapping(target = "id", source = "teacherId")
    Teacher mapTeacherWithId(Integer teacherId);

    @Named("mapClassroomWithId")
    @Mapping(target = "id", source = "classroomId")
    Classroom mapClassroomId(Integer classroomId);
}
