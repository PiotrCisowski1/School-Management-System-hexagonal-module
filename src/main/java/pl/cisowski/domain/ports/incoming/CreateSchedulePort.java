package pl.cisowski.domain.ports.incoming;

import pl.cisowski.domain.model.command.CreateScheduleCommand;
import pl.cisowski.domain.model.schedule.Schedule;

public interface CreateSchedulePort {
    Schedule execute(CreateScheduleCommand command, Integer scheduleVersionId);
}
