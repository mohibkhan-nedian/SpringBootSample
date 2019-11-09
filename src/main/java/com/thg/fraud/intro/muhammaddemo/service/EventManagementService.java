package com.thg.fraud.intro.muhammaddemo.service;

import com.thg.fraud.intro.muhammaddemo.factory.EventFactory;
import com.thg.fraud.intro.muhammaddemo.json.EventResponse;
import com.thg.fraud.intro.muhammaddemo.json.NewEventRequest;
import com.thg.fraud.intro.muhammaddemo.model.domain.Event;
import com.thg.fraud.intro.muhammaddemo.model.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.Future;

@Service
public class EventManagementService {

    private final EventFactory eventFactory;

    private RestTemplate restTemplate;

    public EventManagementService(final EventFactory eventFactory, final RestTemplate restTemplate) {
        this.eventFactory = eventFactory;
        this.restTemplate = restTemplate;
    }

    public String createEvent(final NewEventRequest newEventRequest) {
        try {
            final Event event = eventFactory.getNewEvent(newEventRequest);

            Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();

            String url = "http://mytestservice/products/";

            List<Future<EventResponse>> futureList = new ArrayList<Future<EventResponse>>();

            for (Product product: event.getProducts()) {
                Integer productID= product.getIdentifier();
                final ResponseEntity<EventResponse> responseEntity = restTemplate.exchange(
                        url + productID,
                        HttpMethod.GET,
                        null,
                        com.thg.fraud.intro.muhammaddemo.json.EventResponse.class
                );
                futureList.add(new AsyncResult<>(responseEntity.getBody()));
            }

            for(Future<EventResponse> future : futureList) {
                final EventResponse eventResponse = future.get();
                map.put(eventResponse.getIdentifier(), eventResponse.getInStock());
            }
            return map.toString();
        }
        catch (final Exception e){
            System.out.println(e);
            return null;
        }
    }
}
