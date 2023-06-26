package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.exception.EntityNotFoundException;
import com.example.ticketBookingApp.model.Row;
import com.example.ticketBookingApp.model.Seat;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

@Service
public class RowService {

    public static Set<Row> createRows(int amount, int seatsPerRow) {
        Set<Row> rows = new HashSet<>();
        IntStream.range(0, amount)
                .forEach(x -> {
                    Row row = new Row(x, seatsPerRow);
                    rows.add(row);
                });
        return rows;
    }

    public static Set<Seat> createSeats(int seatsPerRow) {
        Set<Seat> seats = new HashSet<>();
        IntStream.range(0, seatsPerRow)
                .forEach(x -> {
                    Seat seat = new Seat(x);
                    seats.add(seat);
                });
        return seats;
    }

    public static void reserveSeat(Set<Row> rows, int rowNum, int seatNum) throws EntityNotFoundException {
        var row = rows.stream()
                      .filter(r -> r.getRowNum() == rowNum)
                      .findFirst()
                      .orElseThrow(() -> new EntityNotFoundException("row", (long) rowNum));

        var seat = row.getSeats().stream()
                      .filter(s -> s.getSeatNum() == seatNum)
                      .findFirst()
                      .orElseThrow(() -> new EntityNotFoundException("seat", (long) rowNum));

        seat.setFree(false);
    }
}
