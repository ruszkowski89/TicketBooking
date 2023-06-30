package com.example.ticketBookingApp.model;

import com.example.ticketBookingApp.exception.IncorrectReservationException;
import lombok.Data;

@Data
public class Seat {
    private final int seatNum;
    private boolean isTaken;

    public void setTaken(boolean taken) throws IncorrectReservationException {
        if (isTaken() && taken) {
            throw new IncorrectReservationException(String.format("Seat %s is already reserved.", seatNum));
        }
        this.isTaken = taken;
    }
}
