package com.example.ticketBookingApp.exception;

public class IncorrectReservationException extends RuntimeException {

    public IncorrectReservationException(String msg) {
        super(msg);
    }
}
