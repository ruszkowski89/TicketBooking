package com.example.ticketBookingApp.model;

import com.example.ticketBookingApp.service.SeatService;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class Row {
    private int rowNum;
    private Set<Seat> seats;

    public Row(int rowNum, int seatsPerRow) {
        this.rowNum = rowNum;
        this.seats = SeatService.createSeats(seatsPerRow);
    }
}
