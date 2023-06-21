package com.example.ticketBookingApp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@Data
@RedisHash
public class Screening {
    @Id
    private String id;
    private Movie movie;
    private Date date;
    private Room room;
}
