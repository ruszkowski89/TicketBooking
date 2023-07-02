package com.example.ticketbooking.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatServiceTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 154})
    void testCreateSeats(int input) {
        var seats = SeatService.createSeats(input);

        assertEquals(input, seats.size());
    }

}
