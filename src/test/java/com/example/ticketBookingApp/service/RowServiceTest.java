package com.example.ticketBookingApp.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RowServiceTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 225})
    public void testCreateRows(int input) {
        var rows = RowService.createRows(input, input);

        assertEquals(input, rows.length);
    }
}
