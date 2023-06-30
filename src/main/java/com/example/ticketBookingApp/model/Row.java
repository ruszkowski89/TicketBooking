package com.example.ticketBookingApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.ticketBookingApp.service.SeatService.createSeats;

@Data
@NoArgsConstructor
public class Row {
    private int rowNum;
    private Seat[] seats;

    public Row(int rowNum, int seatsPerRow) {
        this.rowNum = rowNum;
        this.seats = createSeats(seatsPerRow);
    }

}
