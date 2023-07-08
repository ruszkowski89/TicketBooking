package com.example.ticketbooking.model.request;


import com.example.ticketbooking.model.Person;
import com.example.ticketbooking.model.TicketType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationRequest {
    @Valid
    private Person person;
    private long screeningId;
    @Size(min = 1)
    private Set<TicketDetails> tickets;

    @Data
    public static class TicketDetails {
        @Positive
        private int rowNum;
        @Positive
        private int seatNum;
        private TicketType ticketType = TicketType.ADULT;
    }
}
