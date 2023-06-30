package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.exception.IncorrectReservationException;
import com.example.ticketBookingApp.model.*;
import com.example.ticketBookingApp.model.request.ReservationRequest;
import com.example.ticketBookingApp.repository.CustomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReservationServiceTest {
    @Autowired
    private ReservationService cut;

    @MockBean
    private ScreeningService screeningService;
    @MockBean
    private RoomService roomService;
    @MockBean
    private CustomRepository<Reservation> repo;

    private Room room;
    private Person person;
    private LocalDateTime time;
    private Screening screening;
    private Set<ReservationRequest.TicketDetails> tickets;



    @BeforeEach
    public void setUp(){
        person = new Person("Test", "Test");
        tickets = new HashSet<>();
        var ticket1 = new ReservationRequest.TicketDetails();
        ticket1.setRowNum(3);
        ticket1.setSeatNum(3);
        tickets.add(ticket1);

        Movie movie = new Movie("Test");
        room = new Room(3, 6);

        time = LocalDateTime.now().plusMinutes(45);
        screening = new Screening(movie, time, room);

        Mockito.when(screeningService.find(screening.getId())).thenReturn(screening);
    }

    @Test
    public void testCreateReservation() {
        Reservation reservation = cut.createReservation(person, screening.getId(), tickets);

        Mockito.verify(roomService).updateRoom(tickets, screening);
        assertEquals(screening, reservation.getScreening());
        assertEquals("Test", reservation.getPerson().getName());
        assertEquals("Test", reservation.getPerson().getSurname());
        assertFalse(reservation.getTickets().isEmpty());
        assertTrue(reservation.getPrice().getPrice() > 0);
    }

    @Test
    public void whenScreeningIsIn10Minutes_thenIncorrectReservationExceptionIsThrown() {
        screening.setDateTime(LocalDateTime.now().plusMinutes(10));
        assertThrows(IncorrectReservationException.class, () -> cut.createReservation(person, screening.getId(), tickets));
    }

    @Test
    public void whenThereIsOneSeatLeftBetweenReservedSeats_thenIncorrectReservationExceptionIsThrown() {
        var ticket2 = new ReservationRequest.TicketDetails();
        ticket2.setRowNum(3);
        ticket2.setSeatNum(5);
        tickets.add(ticket2);

        screening.setDateTime(LocalDateTime.now().plusMinutes(10));
        assertThrows(IncorrectReservationException.class, () -> cut.createReservation(person, screening.getId(), tickets));
    }
}
