package com.example.ticketBookingApp.model;

import com.example.ticketBookingApp.model.request.ReservationRequest;

import java.util.Set;

public class PriceCalculator {

    public static double calculate(Set<ReservationRequest.TicketDetails> ticketType) {
        return ticketType.stream()
                .mapToDouble(t -> getTicketPrice(t.getTicketType()))
                .sum();
    }

    private static double getTicketPrice(TicketType ticketType) {
        switch (ticketType) {
            case CHILD -> {
                return 18;
            }
            case STUDENT -> {
                return 12.50;
            }
            default -> {
                return 25;
            }
        }
    }
}
