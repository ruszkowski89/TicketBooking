package com.example.ticketbooking.service;

import com.example.ticketbooking.exception.EntityNotFoundException;
import com.example.ticketbooking.model.Movie;
import com.example.ticketbooking.model.Room;
import com.example.ticketbooking.model.Row;
import com.example.ticketbooking.model.Screening;
import com.example.ticketbooking.repository.CustomRepository;
import com.example.ticketbooking.repository.MovieRepository;
import com.example.ticketbooking.repository.RoomRepository;
import com.example.ticketbooking.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.List;

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

    //   note: this method is not efficient, because screenings should be already filtered in DB query,
    //   however for this small app I'll leave it as it is
    public List<Screening> getScreeningsForTimeSlot(LocalDateTime from, LocalDateTime to) {
        return screeningRepo.findAll().stream()
                .filter(s -> s.getDateTime().isAfter(from.minusSeconds(1)) && s.getDateTime().isBefore(to.plusSeconds(1)))
                .sorted()
                .toList();
    }

    public List<Row> getRows(long screeningId) throws EntityNotFoundException {
        return find(screeningId).getRoom()
                                .getRows();
    }

    static boolean startsInLessThan15Mins(Screening screening) {
        return screening.getDateTime().isAfter(LocalDateTime.now().minusMinutes(15));
    }
}
