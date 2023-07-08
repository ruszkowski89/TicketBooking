package com.example.ticketbooking.controller;

import com.example.ticketbooking.model.Movie;
import com.example.ticketbooking.model.request.MovieRequest;
import com.example.ticketbooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BasePathAwareController(path = "movies")
public class MovieController extends ParentController<Movie, MovieService> {

    @Autowired
    protected MovieController(MovieService service) {
        super(service);
    }

    @PostMapping
    public ResponseEntity<Movie> post(@RequestBody MovieRequest request) {
        return ResponseEntity.ok(super.service.create(request.getTitle()));
    }

}
