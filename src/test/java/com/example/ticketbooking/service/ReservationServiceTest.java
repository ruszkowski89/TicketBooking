package com.example.ticketbooking.service;

import com.example.ticketbooking.exception.IncorrectReservationException;
import com.example.ticketbooking.model.*;
import com.example.ticketbooking.model.request.ReservationRequest;
import com.example.ticketbooking.repository.CustomRepository;
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
class ReservationServiceTest {
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
        room = new Room();

        time = LocalDateTime.now().plusMinutes(45);
        screening = new Screening(movie, time, room, 3, 6);

        Mockito.when(screeningService.find(screening.getId())).thenReturn(screening);
    }

    @Test
    void testCreateReservation() {
        Reservation reservation = cut.createReservation(person, screening.getId(), tickets, "");

        assertEquals(screening, reservation.getScreening());
        assertEquals("Test", reservation.getPerson().getName());
        assertEquals("Test", reservation.getPerson().getSurname());
        assertFalse(reservation.getTickets().isEmpty());
        assertTrue(reservation.getPrice().getAmount() > 0);
    }

    @Test
    void whenScreeningIsIn10Minutes_thenIncorrectReservationExceptionIsThrown() {
        long screeningId = screening.getId();
        screening.setDateTime(LocalDateTime.now().plusMinutes(10));
        assertThrows(IncorrectReservationException.class, () -> cut.createReservation(person, screeningId, tickets, ""));
    }

    @Test
    void whenThereIsOneSeatLeftBetweenReservedSeats_thenIncorrectReservationExceptionIsThrown() {
        long screeningId = screening.getId();
        var ticket2 = new ReservationRequest.TicketDetails();
        ticket2.setRowNum(3);
        ticket2.setSeatNum(5);
        tickets.add(ticket2);

        screening.setDateTime(LocalDateTime.now().plusMinutes(10));
        assertThrows(IncorrectReservationException.class, () -> cut.createReservation(person, screeningId, tickets, ""));
    }
}
