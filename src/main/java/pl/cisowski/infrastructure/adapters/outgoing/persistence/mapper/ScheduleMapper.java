package pl.cisowski.infrastructure.adapters.outgoing.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.cisowski.domain.model.schedule.Schedule;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.models.ScheduleEntity;

import java.util.List;


@Mapper(componentModel = "spring",
        uses = {ScheduleVersionMapper.class, ExternalBaseMapperHelper.class},
        implementationName = "PersistenceScheduleMapperImpl")
public interface ScheduleMapper {

    List<Schedule> toSchedules(List<ScheduleEntity> scheduleEntities);

    @Mapping(target = "scheduleVersion", source = "scheduleVersion", qualifiedByName = "toScheduleVersion")
    @Mapping(target = "subject", source = "subjectId", qualifiedByName = "toBaseSubject")
    @Mapping(target = "teacher", source = "teacherId", qualifiedByName = "toBaseTeacher")
    @Mapping(target = "classroom", source = "classroomId", qualifiedByName = "toBaseClassroom")
    Schedule toSchedule(ScheduleEntity scheduleEntity);

    @Mapping(target = "scheduleVersion", source = "scheduleVersion", qualifiedByName = "toScheduleVersionEntity")
    @Mapping(target = "subjectId", source = "subject.id")
    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "classroomId", source = "classroom.id")
    ScheduleEntity toScheduleEntity(Schedule schedule);
}
