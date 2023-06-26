package com.example.ticketBookingApp.controller;

import com.example.ticketBookingApp.model.Movie;
import com.example.ticketBookingApp.model.request.MovieRequest;
import com.example.ticketBookingApp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BasePathAwareController(path = "movies")
public class MovieController extends ParentController<Movie> {

    private final MovieService service;

    @Autowired
    protected MovieController(MovieService service) {
        super(service);
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Movie> post(@RequestBody MovieRequest request) {
        return ResponseEntity.ok(service.create(request.getTitle()));
    }

}
