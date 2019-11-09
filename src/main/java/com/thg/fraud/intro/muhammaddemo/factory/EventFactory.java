package com.thg.fraud.intro.muhammaddemo.factory;

import com.thg.fraud.intro.muhammaddemo.json.NewEventRequest;
import com.thg.fraud.intro.muhammaddemo.model.domain.Event;
import org.springframework.stereotype.Component;

@Component
public class EventFactory {

    public Event getNewEvent(final NewEventRequest newEventRequest) {
        return new Event(
                newEventRequest.getIdentifier(),
                newEventRequest.getTimestamp(),
                newEventRequest.getProducts(),
                newEventRequest.getpaymentDetails()
        );
    }
}
