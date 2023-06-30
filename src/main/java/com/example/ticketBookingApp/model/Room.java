package com.example.ticketBookingApp.model;

import com.example.ticketBookingApp.service.RowService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@EqualsAndHashCode(callSuper = true)
@RedisHash
@Data
@NoArgsConstructor
public class Room extends DBModel {
    private Row[] rows;

    public Room(int rowsAmount, int seatsPerRow) {
        this.rows = RowService.createRows(rowsAmount, seatsPerRow);
    }
}
