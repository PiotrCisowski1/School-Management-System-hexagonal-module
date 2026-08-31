package pl.cisowski.infrastructure.adapters.outgoing.persistence.mapper;

import org.mapstruct.*;
import org.springframework.util.CollectionUtils;
import pl.cisowski.domain.model.schedule.ScheduleVersion;
import pl.cisowski.infrastructure.adapters.outgoing.persistence.models.ScheduleVersionEntity;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {ScheduleMapperHelper.class})
public interface ScheduleVersionMapper {

    @Named("toScheduleVersion")
    @Mapping(target = "schedules", source = "schedules", qualifiedByName = "toScheduleList")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "yearbook", ignore = true)
    ScheduleVersion toScheduleVersion(ScheduleVersionEntity entity);

    @AfterMapping
    default void mapScheduleVersionToSchedules(@MappingTarget ScheduleVersion scheduleVersion) {
        if(scheduleVersion == null || CollectionUtils.isEmpty(scheduleVersion.getSchedules()))
            return;

        scheduleVersion.getSchedules().stream()
                .filter(Objects::nonNull)
                .forEach(schedule -> schedule.setScheduleVersion(scheduleVersion));
    }

    @Named("toScheduleVersionEntity")
    @Mapping(target = "yearbookId", source = "yearbook.id")
    @Mapping(target = "schedules", ignore = true)
    ScheduleVersionEntity toScheduleVersionEntityWithoutSchedules(ScheduleVersion scheduleVersion);
}
