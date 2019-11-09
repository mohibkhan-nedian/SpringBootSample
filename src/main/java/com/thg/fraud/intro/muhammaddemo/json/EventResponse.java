package com.thg.fraud.intro.muhammaddemo.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventResponse {

    private Integer identifier;
    private Boolean inStock;

    public EventResponse() {
    }

    public EventResponse(final Integer identifier, final Boolean inStock) {
        this.identifier = identifier;
        this.inStock = inStock;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public Boolean getInStock() {
        return inStock;
    }
}
