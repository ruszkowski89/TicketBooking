package com.example.ticketbooking.service;

import com.example.ticketbooking.exception.EntityNotFoundException;
import com.example.ticketbooking.exception.IncorrectReservationException;
import com.example.ticketbooking.model.*;
import com.example.ticketbooking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.example.ticketbooking.model.request.ReservationRequest.TicketDetails;
import static com.example.ticketbooking.service.RowService.updateRows;
import static com.example.ticketbooking.service.ScreeningService.startsInLessThan15Mins;

@org.springframework.stereotype.Service
public class ReservationService extends ParentService<Reservation> {
    private final ScreeningService screeningService;

    @Autowired
    public ReservationService(ReservationRepository repo, RedisTemplate<String, Object> redisTemplate,
                              ScreeningService screeningService) {
        super(repo, redisTemplate);
        this.screeningService = screeningService;
    }

    @Transactional
    public Reservation createReservation(Person person, long screeningId, Set<TicketDetails> tickets, String reqPath)
            throws EntityNotFoundException, IncorrectReservationException {
        Screening screening = screeningService.find(screeningId);
        verifyIfStillCanBeReserved(screening.getDateTime());

        updateRows(screening.getRows(), tickets);

        LocalDateTime expirationTime = getExpirationTime();
        Price price = new Price(PriceCalculator.calculate(tickets));
        Reservation reservation = new Reservation(person, screening, tickets, expirationTime, price);
        setIdSequence(reservation);
        generateConfirmUri(reqPath, reservation);
        return repo.save(reservation);
    }

    private static void generateConfirmUri(String reqPath, Reservation reservation) {
        URI uri = UriGenerator.generateReservationConfirmUri(reqPath, reservation.getId());
        reservation.setConfirmationLink(uri);
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
    public void cancelReservations() {
        repo.findAll().stream()
            .filter(r -> !r.isConfirmed())
            .filter(r -> isExpired(r) || startsInLessThan15Mins(r.getScreening()))
            .forEach(this::deleteReservation);
    }

    private boolean isExpired(Reservation reservation) {
        return LocalDateTime.now().isAfter(reservation.getExpirationTime());
    }

    @Override
    public void deleteAll() {
        repo.findAll()
            .forEach(this::deleteReservation);
    }
    private void deleteReservation(Reservation r) {
        Screening screening = r.getScreening();
        if (screening != null) {
            var tickets = r.getTickets();
            tickets.forEach(t ->
                    screening.getRows().get(t.getRowNum()-1)
                                       .getSeats()
                                       .get(t.getSeatNum()-1)
                                       .setTaken(false)
            );
        }

        repo.delete(r);
    }

    public Reservation confirm(long id) {
        Reservation reservation = repo.findById(id)
                                      .orElseThrow(() -> new EntityNotFoundException("reservation" , id));
        reservation.setConfirmed(true);
        return repo.save(reservation);
    }
}
