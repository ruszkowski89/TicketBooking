package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.exception.IncorrectReservationException;
import com.example.ticketBookingApp.model.Seat;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class SeatService {

    public static Seat[] createSeats(int seatsPerRow) {
        Seat[] seats = new Seat[seatsPerRow + 1];
        IntStream.range(1, seatsPerRow+1)
                .forEach(x -> {
                    Seat seat = new Seat(x);
                    seats[x] = seat;
                });
        return seats;
    }

    protected static void checkForEmptySeatBetweenSeats(Seat[] seats, int rowNum) {
        Seat prevSeat;
        Seat nextSeat;
        for (int i=2; i<seats.length; i++) {
            prevSeat = seats[i-1];
            Seat thisSeat = seats[i];
            nextSeat = seats[i+1];

            if (!thisSeat.isTaken() && (prevSeat.isTaken()) && nextSeat.isTaken()) {
                throw new IncorrectReservationException(String.format("There is empty seat left between " +
                        "seats %s and %s in row %s", prevSeat, nextSeat, rowNum));
            }
        }
    }
}
