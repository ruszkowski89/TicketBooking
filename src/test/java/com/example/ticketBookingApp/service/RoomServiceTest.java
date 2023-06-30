package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.exception.IncorrectReservationException;
import com.example.ticketBookingApp.exception.LimitReachedException;
import com.example.ticketBookingApp.model.Room;
import com.example.ticketBookingApp.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private RoomService cut;

    @MockBean
    private RoomRepository repo;



    @Test
    public void whenAddRoom_thenRoomIsSaved() throws IncorrectReservationException, LimitReachedException {
        int rows = 7;
        int seatsPerRow = 10;

        Mockito.when(repo.findAll()).thenReturn(new ArrayList<>());

        cut.create(rows, seatsPerRow);

        Mockito.verify(repo).save(any());
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
                () -> cut.create(rows, seatsPerRow)
        );
    }


}
