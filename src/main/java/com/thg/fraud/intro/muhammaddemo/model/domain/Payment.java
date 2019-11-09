package com.thg.fraud.intro.muhammaddemo.model.domain;

public class Payment {

    private String type;
    private String provider;
    private Double amount;
    private String currency;

    public Payment(){

    }

    public Payment(final String type,
                   final String provider,
                   final Double amount,
                   final String currency) {
        this.type = type;
        this.provider = provider;
        this.amount = amount;
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public String getProvider() {
        return provider;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
