package com.example.ticketbooking.model;

import com.example.ticketbooking.service.SeatService;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Row {
    private int rowNum;
    private List<Seat> seats;

    public Row(int rowNum, int seatsPerRow) {
        this.rowNum = rowNum;
        this.seats = SeatService.createSeats(seatsPerRow);
    }

}
