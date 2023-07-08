package com.example.ticketbooking.repository;


import com.example.ticketbooking.model.Reservation;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReservationRepository extends CustomRepository<Reservation> {
}
