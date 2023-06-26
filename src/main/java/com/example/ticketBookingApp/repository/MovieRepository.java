package com.example.ticketBookingApp.repository;

import com.example.ticketBookingApp.model.Movie;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MovieRepository extends CustomRepository<Movie> {
}
