package com.example.ticketbooking.service;

import com.example.ticketbooking.exception.LimitReachedException;
import com.example.ticketbooking.model.Room;
import com.example.ticketbooking.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

@org.springframework.stereotype.Service
public class RoomService extends ParentService<Room> {
    @Value("${numOfRooms:3}")
    private int numOfRooms;
    private final ScreeningService screeningService;

    @Autowired
    public RoomService(CustomRepository<Room> repo, RedisTemplate<String, Object> redisTemplate,
                       ScreeningService screeningService) {
        super(repo, redisTemplate);
        this.screeningService = screeningService;
    }

    public Room create() throws LimitReachedException {
        if (isRoomsLimitReached()) {
            throw new LimitReachedException("rooms");
        }

        Room room = new Room();
        setIdSequence(room);
        return repo.save(room);
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
        screeningService.deleteAll();
    }

    public boolean isRoomsLimitReached() {
        return repo.findAll().size() >= numOfRooms;
    }
}
