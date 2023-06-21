package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.exception.LimitReachedException;
import com.example.ticketBookingApp.model.Room;
import com.example.ticketBookingApp.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private RoomService cut;

    @MockBean
    private RoomRepository repo;



    @Test
    public void whenAddRoom_thenRoomIsSaved() throws LimitReachedException {
        int rows = 7;
        int seatsPerRow = 10;

        Mockito.when(repo.findAll()).thenReturn(new ArrayList<>());

        cut.addRoom(rows, seatsPerRow);

        Mockito.verify(repo).save(new Room(rows, seatsPerRow));
    }

    @Test
    public void whenAddRoom_thenCorrectSeatsAmountIsCreated() throws LimitReachedException {
        int rows = 7;
        int seatsPerRow = 10;

        Mockito.when(repo.findAll()).thenReturn(new ArrayList<>());

        Room room = cut.addRoom(rows, seatsPerRow);

        assertTrue(
                Arrays.stream(room.getRows())
                      .allMatch(r -> r.getSeats().length == 10)
        );
    }

    @Test
    public void givenRoomNumExceeded_whenAddRoom_limitReachedExceptionIsThrown() {
        int rows = 7;
        int seatsPerRow = 10;

        var rooms = List.of(
                new Room(1, seatsPerRow),
                new Room(1, seatsPerRow),
                new Room(1, seatsPerRow)
        );
        Mockito.when(repo.findAll()).thenReturn(rooms);

        assertThrows(
                LimitReachedException.class,
                () -> cut.addRoom(rows, seatsPerRow)
        );
    }


}
