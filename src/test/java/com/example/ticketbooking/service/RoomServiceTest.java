package com.example.ticketbooking.service;

import com.example.ticketbooking.exception.IncorrectReservationException;
import com.example.ticketbooking.exception.LimitReachedException;
import com.example.ticketbooking.model.Room;
import com.example.ticketbooking.repository.RoomRepository;
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
class RoomServiceTest {

    @Autowired
    private RoomService cut;

    @MockBean
    private RoomRepository repo;


    @Test
    void whenAddRoom_thenRoomIsSaved() throws IncorrectReservationException, LimitReachedException {
        Mockito.when(repo.findAll()).thenReturn(new ArrayList<>());

        cut.create();

        Mockito.verify(repo).save(any());
    }

    @Test
    void givenRoomNumExceeded_whenAddRoom_limitReachedExceptionIsThrown() {
        var rooms = List.of(
                new Room(),
                new Room(),
                new Room()
        );
        Mockito.when(repo.findAll()).thenReturn(rooms);

        assertThrows(
                LimitReachedException.class,
                () -> cut.create()
        );
    }


}
