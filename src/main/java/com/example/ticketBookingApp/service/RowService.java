package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.exception.EntityNotFoundException;
import com.example.ticketBookingApp.model.Row;
import com.example.ticketBookingApp.model.request.ReservationRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;

@Service
public class RowService {

    public static Row[] createRows(int amount, int seatsPerRow) {
        Row[] rows = new Row[amount + 1];
        IntStream.range(1, amount+1)
                .forEach(x -> {
                    Row row = new Row(x, seatsPerRow);
                    rows[x] = row;
                });
        return rows;
    }

    protected static void updateRows(Row[] rows, Set<ReservationRequest.TicketDetails> tickets)
            throws EntityNotFoundException {
        var rowToTicketsMap = tickets.stream().collect(groupingBy(x -> rows[x.getRowNum()]));

        rowToTicketsMap.forEach((r, t) -> {
            tickets.forEach(ticket -> r.getSeats()[ticket.getSeatNum()].setTaken(true));
            SeatService.checkForEmptySeatBetweenSeats(r.getSeats(), r.getRowNum());
        });
    }

}
