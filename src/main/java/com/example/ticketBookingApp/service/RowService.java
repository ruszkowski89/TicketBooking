package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.model.Row;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

@Service
public class RowService {

    public static Set<Row> createRows(int amount, int seatsPerRow) {
        Set<Row> rows = new HashSet<>();
        IntStream.range(1, amount+1)
                .forEach(x -> {
                    Row row = new Row(x, seatsPerRow);
                    rows.add(row);
                });
        return rows;
    }
}
