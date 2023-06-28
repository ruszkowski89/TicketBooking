package com.example.ticketBookingApp.exception;

public class SeatAlreadyReservedException extends RuntimeException {

    public SeatAlreadyReservedException(int seatNum) {
        super(String.format("Seat %s is already reserved.", seatNum));
    }
}
