package com.thg.fraud.intro.muhammaddemo.json;

import com.thg.fraud.intro.muhammaddemo.model.domain.Payment;
import com.thg.fraud.intro.muhammaddemo.model.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

public class NewEventRequest {

    private String identifier;
    private Long timestamp;
    private List<Product> products;
    private Payment paymentDetails;

    public NewEventRequest(){

    }

    public NewEventRequest(final String identifier,
                 final Long timestamp,
                 final List<Product> products,
                 final Payment paymentDetails) {
        this.identifier = identifier;
        this.timestamp = timestamp;
        this.products = products;
        this.paymentDetails = paymentDetails;
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

    public Payment getpaymentDetails() {
        return paymentDetails;
    }
}
