package com.example.ticketBookingApp.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String objectType, long id) {
        super(String.format("Sorry, %s with id: %s not found.", objectType, id));
    }
}
