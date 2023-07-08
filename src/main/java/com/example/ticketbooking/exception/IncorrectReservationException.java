package com.example.ticketbooking.exception;

public class IncorrectReservationException extends RuntimeException {

    public IncorrectReservationException(String msg) {
        super(msg);
    }
}
