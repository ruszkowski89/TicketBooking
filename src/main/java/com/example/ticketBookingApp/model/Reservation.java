package com.example.ticketBookingApp.model;

import com.example.ticketBookingApp.model.request.ReservationRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@RedisHash
public class Reservation extends DBModel {
    private Person person;
    private Screening screening;
    private Set<ReservationRequest.TicketDetails> tickets;
    private LocalDateTime expirationTime;
    private Price price;

    public Reservation(Person person, Screening screening, Set<ReservationRequest.TicketDetails> tickets,
                       LocalDateTime expirationTime, Price price) {
        this.person = person;
        this.screening = screening;
        this.tickets = tickets;
        this.expirationTime = expirationTime;
        this.price = price;
    }
}
