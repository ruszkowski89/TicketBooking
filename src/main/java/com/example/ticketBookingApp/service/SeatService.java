package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.model.Seat;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

@Service
public class SeatService {

    public static Set<Seat> createSeats(int seatsPerRow) {
        Set<Seat> seats = new HashSet<>();
        IntStream.range(1, seatsPerRow+1)
                .forEach(x -> {
                    Seat seat = new Seat(x);
                    seats.add(seat);
                });
        return seats;
    }

}
