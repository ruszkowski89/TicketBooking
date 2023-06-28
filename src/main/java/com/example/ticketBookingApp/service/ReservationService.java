package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.exception.EntityNotFoundException;
import com.example.ticketBookingApp.exception.ItsTooLateException;
import com.example.ticketBookingApp.exception.SeatAlreadyReservedException;
import com.example.ticketBookingApp.model.*;
import com.example.ticketBookingApp.model.request.ReservationRequest;
import com.example.ticketBookingApp.repository.CustomRepository;
import org.apache.commons.lang3.stream.Streams;
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
    public ReservationService(CustomRepository<Reservation> repo, RedisTemplate<String, Object> redisTemplate,
                              ScreeningService screeningService, RoomService roomService) {
        super(repo, redisTemplate);
        this.screeningService = screeningService;
        this.roomService = roomService;
    }

    @Transactional
    public Reservation create(Person person, long screeningId, Set<ReservationRequest.TicketDetails> tickets)
            throws EntityNotFoundException, SeatAlreadyReservedException, ItsTooLateException {
        Screening screening = screeningService.find(screeningId);
        verifyIfStillCanBeReserved(screening.getDateTime());

        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(15);
        Price price = new Price(PriceCalculator.calculate(tickets));
        Reservation reservation = new Reservation(person, screening, tickets, expirationTime, price);

        Streams.stream(tickets).forEach(t -> changeReservation(screening.getRoom(), t.getRowNum(), t.getSeatNum(), true));
        roomService.update(screening.getRoom());

        setIdSequence(reservation);
        return repo.save(reservation);
    }

    private void changeReservation(Room room, int rowNum, int seatNum, boolean setSeatTaken) throws EntityNotFoundException, SeatAlreadyReservedException {
        Row row = room.getRows().stream()
                .filter(r -> r.getRowNum() == rowNum)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("row", rowNum));

        Seat seat = row.getSeats().stream()
                .filter(s -> s.getSeatNum() == seatNum)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("seat", seatNum));;

        seat.setTaken(setSeatTaken);
    }

    private static void verifyIfStillCanBeReserved(LocalDateTime screeningTime) throws ItsTooLateException {
        LocalDateTime timeLimit = screeningTime.minusMinutes(15);
        if (LocalDateTime.now().isAfter(timeLimit)) {
            throw new ItsTooLateException();
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
        Streams.stream(tickets).forEach(t -> changeReservation(room, t.getRowNum(), t.getSeatNum(), false));
        roomService.update(room);
        repo.delete(r);
    }
}
