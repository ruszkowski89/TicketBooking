package com.example.ticketBookingApp.model.request;


import com.example.ticketBookingApp.model.Person;
import com.example.ticketBookingApp.model.TicketType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationRequest {
    private Person person;
    private long screeningId;
    private Set<TicketDetails> tickets;

    @Data
    public static class TicketDetails {
        private int rowNum;
        private int seatNum;
        private TicketType ticketType;
    }
}
