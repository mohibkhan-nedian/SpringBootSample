package com.thg.fraud.intro.muhammaddemo.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    private String identifier;
    private Long timestamp;
    private List<Product> products;
    private Payment payment;

    public Event(){

    }

    public Event(final String identifier,
                 final Long timestamp,
                 final List<Product> products,
                 final Payment payment) {
        this.identifier = identifier;
        this.timestamp = timestamp;
        this.products = products;
        this.payment = payment;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Payment getPayment() {
        return payment;
    }
}
