package pl.cisowski.infrastructure.adapters.outgoing.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.cisowski.domain.model.schedule.Schedule;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.models.ScheduleEntity;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapperHelper {

    @Named("toScheduleList")
    Collection<Schedule> toScheduleList(Collection<ScheduleEntity> schedules);

    @Mapping(target = "scheduleVersion", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "classroom", ignore = true)
    Schedule toSchedule(ScheduleEntity scheduleEntity);
}
