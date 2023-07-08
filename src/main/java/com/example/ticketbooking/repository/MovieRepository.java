package com.example.ticketbooking.repository;

import com.example.ticketbooking.model.Movie;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MovieRepository extends CustomRepository<Movie> {
}
