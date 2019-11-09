package com.thg.fraud.intro.muhammaddemo.facade;

import com.thg.fraud.intro.muhammaddemo.json.NewEventRequest;
import com.thg.fraud.intro.muhammaddemo.service.EventManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class EventManageResource {

    private final EventManagementService service;

    public EventManageResource(EventManagementService service) {
        this.service = service;
    }

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @PostMapping(path = "/events")
    @ResponseStatus(HttpStatus.CREATED)
    public String createEvent(@RequestBody final NewEventRequest newEventRequest) {
        return service.createEvent(newEventRequest);
    }

}
