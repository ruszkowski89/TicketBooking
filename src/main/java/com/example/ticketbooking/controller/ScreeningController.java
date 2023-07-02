package com.example.ticketbooking.controller;

import com.example.ticketbooking.exception.EntityNotFoundException;
import com.example.ticketbooking.model.Screening;
import com.example.ticketbooking.model.request.ScreeningGetRequest;
import com.example.ticketbooking.model.request.ScreeningPostRequest;
import com.example.ticketbooking.service.ScreeningService;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BasePathAwareController(path = "screenings")
public class ScreeningController extends ParentController<Screening> {

    private final ScreeningService service;

    public ScreeningController(ScreeningService service) {
        super(service);
        this.service = service;
    }

    @PostMapping
    public ResponseEntity post(@RequestBody ScreeningPostRequest req) {
        try {
            return ResponseEntity.ok(service.create(req.getMovieId(), req.getDate(), req.getRoomId()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(e.getMessage());
        }
    }

    @GetMapping(path = "seats/{screeningId}")
    public ResponseEntity getSeats(@PathVariable long screeningId) {
        try {
            return ResponseEntity.ok(service.getRows(screeningId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping(path = "timeSlot")
    public ResponseEntity getScreeningsForTimeSlot(@RequestBody ScreeningGetRequest req) {
        return ResponseEntity.ok(service.getScreeningsForTimeSlot(req.getFrom(), req.getTo()));
    }
}