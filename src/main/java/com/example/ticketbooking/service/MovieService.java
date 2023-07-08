package com.example.ticketbooking.service;

import com.example.ticketbooking.model.Movie;
import com.example.ticketbooking.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@org.springframework.stereotype.Service
public class MovieService extends ParentService<Movie> {

    @Autowired
    public MovieService(MovieRepository repo, RedisTemplate<String, Object> redisTemplate) {
        super(repo, redisTemplate);
    }

    public Movie create(String title) {
        Movie movie = new Movie(title);
        setIdSequence(movie);
        return repo.save(movie);
    }
}
