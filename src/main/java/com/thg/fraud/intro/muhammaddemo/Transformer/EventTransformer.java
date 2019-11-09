package com.thg.fraud.intro.muhammaddemo.Transformer;

import com.thg.fraud.intro.muhammaddemo.json.EventDocument;
import org.springframework.stereotype.Component;

@Component
public class EventTransformer {

    public EventDocument toJson(final com.thg.fraud.intro.muhammaddemo.model.domain.Event event) {
        return new EventDocument(event.getIdentifier(), event.getTimestamp(), event.getProducts(), event.getPayment());
    }
}
