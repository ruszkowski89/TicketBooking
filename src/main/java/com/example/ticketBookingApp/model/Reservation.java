package com.example.ticketBookingApp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@RedisHash
public class Reservation extends DBModel {
    private String name;
    private Screening screening;
    private int rowNum;
    private int seatNum;

    public Reservation(String name, Screening screening, int rowNum, int seatNum) {
        this.name = name;
        this.screening = screening;
        this.rowNum = rowNum;
        this.seatNum = seatNum;
    }
}
