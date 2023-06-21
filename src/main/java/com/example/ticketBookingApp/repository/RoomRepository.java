package com.example.ticketBookingApp.repository;

import com.example.ticketBookingApp.model.Room;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoomRepository extends CustomCrudRepository<Room> {
}
