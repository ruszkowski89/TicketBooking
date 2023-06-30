package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.exception.EntityNotFoundException;
import com.example.ticketBookingApp.exception.IncorrectReservationException;
import com.example.ticketBookingApp.model.*;
import com.example.ticketBookingApp.model.request.ReservationRequest;
import com.example.ticketBookingApp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@org.springframework.stereotype.Service
public class ReservationService extends ParentService<Reservation> {
    private final ScreeningService screeningService;
    private final RoomService roomService;

    @Autowired
    public ReservationService(ReservationRepository repo, RedisTemplate<String, Object> redisTemplate,
                              ScreeningService screeningService, RoomService roomService) {
        super(repo, redisTemplate);
        this.screeningService = screeningService;
        this.roomService = roomService;
    }

    @Transactional
    public Reservation createReservation(Person person, long screeningId, Set<ReservationRequest.TicketDetails> tickets)
            throws EntityNotFoundException, IncorrectReservationException {
        Screening screening = screeningService.find(screeningId);
        verifyIfStillCanBeReserved(screening.getDateTime());

        roomService.updateRoom(tickets, screening);

        LocalDateTime expirationTime = getExpirationTime();
        Price price = new Price(PriceCalculator.calculate(tickets));
        Reservation reservation = new Reservation(person, screening, tickets, expirationTime, price);
        setIdSequence(reservation);
        return repo.save(reservation);
    }



    private static LocalDateTime getExpirationTime() {
        return LocalDateTime.now().plusMinutes(15);
    }

    private static void verifyIfStillCanBeReserved(LocalDateTime screeningTime) throws IncorrectReservationException {
        LocalDateTime timeLimit = screeningTime.minusMinutes(15);
        if (LocalDateTime.now().isAfter(timeLimit)) {
            throw new IncorrectReservationException("Seats can be booked at latest 15 minutes before the screening begins.");
        }
    }

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 1, initialDelay = 1)
    public void expireReservations() {
        repo.findAll().stream()
            .filter(r -> LocalDateTime.now().isAfter(r.getExpirationTime()))
            .forEach(this::deleteReservation);
    }

    public void cancelReservation(long id) {
        Optional<Reservation> reservation = repo.findById(id);
        reservation.ifPresent(this::deleteReservation);
    }

    @Override
    public void deleteAll() {
        repo.findAll()
            .forEach(this::deleteReservation);
    }
    private void deleteReservation(Reservation r) {
        Screening screening = r.getScreening();
        Room room = screening.getRoom();
        var tickets = r.getTickets();
        tickets.forEach(t -> {
            room.getRows()[t.getRowNum()]
                .getSeats()[t.getSeatNum()]
                .setTaken(false);
        });
        roomService.update(room);
        repo.delete(r);
    }
}
