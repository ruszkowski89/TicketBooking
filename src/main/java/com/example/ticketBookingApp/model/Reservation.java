package com.example.ticketBookingApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@RedisHash
public class Reservation {
    @Id
    private String id;
    private String name;
    private Screening screening;
    private Row row;
    private Seat seat;
}
