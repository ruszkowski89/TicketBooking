package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.model.Row;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class RowService {
    public static Row[] createRows(int amount, int seatsPerRow) {
        var rows = new Row[amount];
        IntStream.range(0, amount)
                .forEach(x -> {
                    Row row = new Row(x, seatsPerRow);
                    rows[x] = row;
                });
        return rows;
    }
}
