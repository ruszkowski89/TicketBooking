package com.example.ticketbooking.controller;

import com.example.ticketbooking.exception.EntityNotFoundException;
import com.example.ticketbooking.exception.IncorrectReservationException;
import com.example.ticketbooking.model.Reservation;
import com.example.ticketbooking.model.request.ReservationRequest;
import com.example.ticketbooking.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BasePathAwareController(path = "reservations")
public class ReservationController extends ParentController<Reservation, ReservationService> {
    public static final String CONFIRM_PATH = "/{id}/confirm";

    public ReservationController(ReservationService service) {
        super(service);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody @Valid ReservationRequest req, HttpServletRequest servlet) {
        try {
            return ResponseEntity.ok(
                    service.createReservation(
                            req.getPerson(), req.getScreeningId(),
                            req.getTickets(), servlet.getRequestURL().toString()));

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IncorrectReservationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = CONFIRM_PATH)
    public ResponseEntity post(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(service.confirm(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
