package com.example.ticketBookingApp.model;

import com.example.ticketBookingApp.exception.SeatAlreadyReservedException;
import lombok.Data;

@Data
public class Seat {
    private final int seatNum;
    private boolean isTaken;

    public void setTaken(boolean taken) throws SeatAlreadyReservedException {
        if (isTaken() && taken) {
            throw new SeatAlreadyReservedException(getSeatNum());
        }
        this.isTaken = taken;
    }
}
