package com.example.ticketbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    private double amount;
    private String currency = "PLN";

    public Price(double amount) {
        this.amount = amount;
    }
}
