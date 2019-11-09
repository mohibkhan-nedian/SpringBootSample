package com.thg.fraud.intro.muhammaddemo.json;

import com.thg.fraud.intro.muhammaddemo.model.domain.Payment;
import com.thg.fraud.intro.muhammaddemo.model.domain.Product;

import java.util.List;

public class EventDocument {
    private String identifier;
    private Long timestamp;
    private List<Product> products;
    private Payment payment;

    public EventDocument(){
    }

    public EventDocument(final String identifier,
                 final Long timestamp,
                 final List<Product> products,
                 final Payment payment) {
        this.identifier = identifier;
        this.timestamp = timestamp;
        this.products = products;
        this.payment = payment;
    }

}
