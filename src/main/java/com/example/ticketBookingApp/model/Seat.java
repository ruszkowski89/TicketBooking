package com.example.ticketBookingApp.model;

import lombok.Data;

@Data
public class Seat {
    private final int seatNum;
    private boolean isFree = true;
}
