package com.example.ticketBookingApp.repository;


import com.example.ticketBookingApp.model.Reservation;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReservationRepository extends CustomRepository<Reservation> {
}
