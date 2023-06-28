package com.example.ticketBookingApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    private double price;
    private String currency = "PLN";

    public Price(double price) {
        this.price = price;
    }
}
