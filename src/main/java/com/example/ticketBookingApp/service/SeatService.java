package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.model.Seat;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class SeatService {

    public static Seat[] createSeats(int seatsPerRow) {
        var seats = new Seat[seatsPerRow];
        IntStream.range(0, seatsPerRow)
                .forEach(x -> {
                    Seat seat = new Seat(x);
                    seats[x] = seat;
                });
        return seats;
    }
}
