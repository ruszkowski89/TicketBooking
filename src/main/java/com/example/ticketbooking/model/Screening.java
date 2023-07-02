package com.example.ticketbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

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

    @Override
    public int compareTo(Screening o) {
            int result = movie.getTitle().compareToIgnoreCase(o.getMovie().getTitle());
            if(result==0) {
                return dateTime.compareTo(o.getDateTime());
            }
            else {
                return result;
            }
    }
}
