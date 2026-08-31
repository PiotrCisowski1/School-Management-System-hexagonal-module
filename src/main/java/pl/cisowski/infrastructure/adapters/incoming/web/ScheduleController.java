package pl.cisowski.infrastructure.adapters.incoming.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.cisowski.domain.model.command.CreateScheduleCommand;
import pl.cisowski.domain.model.schedule.Schedule;
import pl.cisowski.domain.ports.incoming.CreateSchedulePort;
import pl.cisowski.infrastructure.adapters.incoming.models.schedule.request.CreateScheduleRequest;
import pl.cisowski.infrastructure.adapters.incoming.models.schedule.response.ScheduleDetailedResponse;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final CreateSchedulePort createSchedulePort;
    private final ScheduleIncomingMapper mapper;

    @PostMapping("/version/{scheduleVersionId}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<ScheduleDetailedResponse> addSchedule(@Valid @RequestBody CreateScheduleRequest request, @PathVariable Integer scheduleVersionId){
        CreateScheduleCommand command = mapper.toCreateScheduleCommand(request);
        Schedule createdSchedule = createSchedulePort.execute(command, scheduleVersionId);
        return new ResponseEntity<>(mapper.toScheduleDetailedResponse(createdSchedule), HttpStatus.CREATED);
    }
}
