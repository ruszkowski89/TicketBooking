package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.exception.EntityNotFoundException;
import com.example.ticketBookingApp.model.Movie;
import com.example.ticketBookingApp.model.Room;
import com.example.ticketBookingApp.model.Row;
import com.example.ticketBookingApp.model.Screening;
import com.example.ticketBookingApp.repository.CustomRepository;
import com.example.ticketBookingApp.repository.MovieRepository;
import com.example.ticketBookingApp.repository.RoomRepository;
import com.example.ticketBookingApp.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.Set;

@org.springframework.stereotype.Service
public class ScreeningService extends ParentService<Screening> {

    private final MovieRepository movieRepo;
    private final RoomRepository roomRepo;
    private final ScreeningRepository screeningRepo;

    @Autowired
    public ScreeningService(CustomRepository<Screening> repo, RedisTemplate<String, Object> redisTemplate,
                            MovieRepository movieRepo, RoomRepository roomRepo, ScreeningRepository screeningRepo) {
        super(repo, redisTemplate);
        this.movieRepo = movieRepo;
        this.roomRepo = roomRepo;
        this.screeningRepo = screeningRepo;
    }

    public Screening create(long movieId, LocalDateTime dateTime, long roomId) throws EntityNotFoundException {
        Movie movie = movieRepo.findById(movieId).orElseThrow(() -> new EntityNotFoundException("movie", movieId));
        Room room = roomRepo.findById(roomId).orElseThrow(() -> new EntityNotFoundException("room", roomId));

        Screening screening = new Screening(movie, dateTime, room);
        setIdSequence(screening);
        return repo.save(screening);
    }

    public Screening find(Long screeningId) throws EntityNotFoundException {
        return screeningRepo.findById(screeningId)
                            .orElseThrow(() -> new EntityNotFoundException("screening", screeningId));
    }

/*    public void deleteScreeningsForRoom(int roomId) {
        List<Screening> screenings = screeningRepo.findByRoomId(roomId);
        screenings.forEach(repo::delete);
    }*/

    public Set<Row> getSeats(long screeningId) throws EntityNotFoundException {
        return find(screeningId).getRoom()
                                .getRows();
    }
}
