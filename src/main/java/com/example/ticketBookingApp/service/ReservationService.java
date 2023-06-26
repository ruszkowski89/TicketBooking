package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.exception.EntityNotFoundException;
import com.example.ticketBookingApp.model.Reservation;
import com.example.ticketBookingApp.model.Screening;
import com.example.ticketBookingApp.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

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

    public Reservation create(String name, long screeningId, int row, int seat) throws EntityNotFoundException {
        Screening screening = screeningService.find(screeningId);

        RowService.reserveSeat(screening.getRoom().getRows(), row, seat);
        roomService.update(screening.getRoom());

        Reservation reservation = new Reservation(name, screening, row, seat);
        setIdSequence(reservation);
        return repo.save(reservation);
    }
}
