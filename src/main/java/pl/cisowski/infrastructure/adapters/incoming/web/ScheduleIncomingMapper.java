package pl.cisowski.infrastructure.adapters.incoming.web;

import org.mapstruct.Mapper;
import pl.cisowski.domain.model.command.CreateScheduleCommand;
import pl.cisowski.domain.model.schedule.Schedule;
import pl.cisowski.infrastructure.adapters.incoming.models.schedule.request.CreateScheduleRequest;
import pl.cisowski.infrastructure.adapters.incoming.models.schedule.response.ScheduleDetailedResponse;

@Mapper(componentModel = "spring")
public interface ScheduleIncomingMapper {
    CreateScheduleCommand toCreateScheduleCommand(CreateScheduleRequest request);
    ScheduleDetailedResponse toScheduleDetailedResponse(Schedule schedule);
}
