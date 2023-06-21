package com.example.ticketBookingApp.model;

import com.example.ticketBookingApp.service.RowService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@Data
@NoArgsConstructor
public class Room {
    @Id
    private Long id;
    private Row[] rows;

    public Room(int rowsAmount, int seatsPerRow) {
        this.rows = RowService.createRows(rowsAmount, seatsPerRow);
    }
}
