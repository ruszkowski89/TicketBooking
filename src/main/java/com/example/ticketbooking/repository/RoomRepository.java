package com.example.ticketbooking.repository;

import com.example.ticketbooking.model.Room;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoomRepository extends CustomRepository<Room> {
}
