package com.example.ticketbooking.model;

import com.example.ticketbooking.model.request.ReservationRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.redis.core.RedisHash;

import java.net.URI;
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
    // It would be better to create 2nd Reservation object, without confirmationLink parameter for Database Storage
    @Transient
    private URI confirmationLink;
    private boolean confirmed;

    public Reservation(Person person, Screening screening, Set<ReservationRequest.TicketDetails> tickets,
                       LocalDateTime expirationTime, Price price) {
        this.person = person;
        this.screening = screening;
        this.tickets = tickets;
        this.expirationTime = expirationTime;
        this.price = price;
    }
}
