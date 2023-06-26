package com.example.ticketBookingApp.model.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationRequest {
    private String name;
    private long screeningId;
    private int rowNum;
    private int seatNum;
}
