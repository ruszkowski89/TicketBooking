package com.example.ticketBookingApp.repository;

import com.example.ticketBookingApp.model.Screening;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ScreeningRepository extends CustomCrudRepository<Screening> {
}
