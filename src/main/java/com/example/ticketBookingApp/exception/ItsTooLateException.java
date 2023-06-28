package com.example.ticketBookingApp.exception;

public class ItsTooLateException extends Throwable {

    public ItsTooLateException() {
        super("Seats can be booked at latest 15 minutes before the screening begins.");
    }
}
