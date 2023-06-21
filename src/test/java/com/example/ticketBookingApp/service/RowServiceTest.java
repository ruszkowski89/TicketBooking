package com.example.ticketBookingApp.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RowServiceTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 225})
    public void whenCreateRows_thenCorrectRowsAmountIsCreated(int input) {
        var rows = RowService.createRows(input, input);

        assertEquals(input, rows.length);
        assertEquals(input-1, rows[input-1].getRowNum());
    }
}
