package com.example.ticketbooking.service;

import com.example.ticketbooking.exception.EntityNotFoundException;
import com.example.ticketbooking.model.Row;
import com.example.ticketbooking.model.request.ReservationRequest;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;

@UtilityClass
public class RowService {

    public static List<Row> createRows(int amount, int seatsPerRow) {
        List<Row> rows = new ArrayList<>();
        IntStream.range(1, amount+1)
                .forEach(x -> {
                    Row row = new Row(x, seatsPerRow);
                    rows.add(row);
                });
        return rows;
    }

    static void updateRows(List<Row> rows, Set<ReservationRequest.TicketDetails> tickets)
            throws EntityNotFoundException {
        var rowToTicketsMap = tickets.stream().collect(groupingBy(x -> rows.get(x.getRowNum() - 1)));

        rowToTicketsMap.forEach((r, t) -> {
            t.forEach(ticket -> r.getSeats().get(ticket.getSeatNum() - 1).setTaken(true));
            SeatService.checkForEmptySeatBetweenSeats(r.getSeats(), r.getRowNum());
        });
    }

}
