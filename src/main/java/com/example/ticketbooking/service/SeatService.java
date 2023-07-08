package com.example.ticketbooking.service;

import com.example.ticketbooking.exception.IncorrectReservationException;
import com.example.ticketbooking.model.Seat;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@UtilityClass
public class SeatService {

    public static List<Seat> createSeats(int seatsPerRow) {
        ArrayList<Seat> seats = new ArrayList<>();
        IntStream.range(1, seatsPerRow + 1)
                .forEach(x -> {
                    Seat seat = new Seat(x);
                    seats.add(seat);
                });
        return seats;
    }

    static void checkForEmptySeatBetweenSeats(List<Seat> seats, int rowNum) {
        IntStream.range(1, seats.size()-1)
                .forEach(s -> {
                    Seat prevSeat = seats.get(s - 1);
                    Seat thisSeat = seats.get(s);
                    Seat nextSeat = seats.get(s + 1);

                    if (!thisSeat.isTaken() && (prevSeat.isTaken()) && nextSeat.isTaken()) {
                        throw new IncorrectReservationException(String.format("There is empty seat left between " +
                                "seats %s and %s in row %s", prevSeat, nextSeat, rowNum));
                    }
                });
    }
}
