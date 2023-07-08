package com.example.ticketbooking.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScreeningPostRequest {
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime date;
    @Positive
    private long movieId;
    @Positive
    private long roomId;
    @Positive
    private int rowsAmount;
    @Positive
    private int seatsPerRow;
}
