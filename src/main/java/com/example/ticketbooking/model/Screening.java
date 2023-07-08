package com.example.ticketbooking.model;

import com.example.ticketbooking.service.RowService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@RedisHash
public class Screening extends DBModel implements Comparable<Screening> {
    private Movie movie;
    private LocalDateTime dateTime;
    @Indexed
    @Reference
    private Room room;
    private List<Row> rows;

    public Screening(Movie movie, LocalDateTime dateTime, Room room, int rowsAmount, int seatsPerRow) {
        this.movie = movie;
        this.dateTime = dateTime;
        this.room = room;
        this.rows = RowService.createRows(rowsAmount, seatsPerRow);
    }

    @Override
    public int compareTo(Screening o) {
        int result = movie.getTitle().compareToIgnoreCase(o.getMovie().getTitle());
        if (result == 0) {
            return dateTime.compareTo(o.getDateTime());
        } else {
            return result;
        }
    }
}
