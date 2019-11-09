package com.thg.fraud.intro.muhammaddemo.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Product {

    private Integer identifier;
    private String title;
    private String quantity;

    public Product() {
    }

    public Product(final Integer identifier,
                   final String title,
                   final String quantity){
        this.identifier = identifier;
        this.title = title;
        this.quantity = quantity;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public String getTitle() {
        return title;
    }

    public String getQuantity() {
        return quantity;
    }

}
