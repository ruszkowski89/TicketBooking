package com.example.ticketBookingApp.controller;

import com.example.ticketBookingApp.exception.EntityNotFoundException;
import com.example.ticketBookingApp.exception.ItsTooLateException;
import com.example.ticketBookingApp.exception.SeatAlreadyReservedException;
import com.example.ticketBookingApp.model.Reservation;
import com.example.ticketBookingApp.model.request.ReservationRequest;
import com.example.ticketBookingApp.service.ReservationService;
import com.example.ticketBookingApp.service.ValidationService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BasePathAwareController(path = "reservations")
public class ReservationController extends ParentController<Reservation> {
    private final ReservationService service;
    private final ValidationService validator;

    public ReservationController(ReservationService service, ValidationService validator) {
        super(service);
        this.service = service;
        this.validator = validator;
    }

    @PostMapping
    public ResponseEntity post(@RequestBody ReservationRequest req) {
        try {
            validator.validate(req);
            return ResponseEntity.ok(service.create(req.getPerson(), req.getScreeningId(), req.getTickets()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (SeatAlreadyReservedException | ItsTooLateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
