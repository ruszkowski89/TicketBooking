package com.example.ticketbooking.model;

import com.example.ticketbooking.exception.IncorrectReservationException;
import lombok.Data;

@Data
public class Seat {
    private final int seatNum;
    private boolean taken;

    public void setTaken(boolean taken) throws IncorrectReservationException {
        if (isTaken() && taken) {
            throw new IncorrectReservationException(String.format("Seat %s is already reserved.", seatNum));
        }
        this.taken = taken;
    }
}
