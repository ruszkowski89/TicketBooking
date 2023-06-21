package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.exception.LimitReachedException;
import com.example.ticketBookingApp.model.Room;
import com.example.ticketBookingApp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Value("${numOfRooms:3}")
    private int numOfRooms;

    private final RoomRepository roomRepo;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RoomService(RoomRepository roomRepository, RedisTemplate<String, Object> redisTemplate) {
        this.roomRepo = roomRepository;
        this.redisTemplate = redisTemplate;
    }

    public Room addRoom(int rowsAmount, int seatsPerRow) throws LimitReachedException {
        if (isRoomsLimitReached()) {
            throw new LimitReachedException("rooms");
        }

        Room room = new Room(rowsAmount, seatsPerRow);
        var opsForValue = redisTemplate.opsForValue();
        room.setId(opsForValue.increment("sequence", 1));
        return roomRepo.save(room);
    }

    public boolean isRoomsLimitReached() {
        return roomRepo.findAll().size() >= numOfRooms;
    }

    public void deleteAll() {
        roomRepo.deleteAll();
    }
}
