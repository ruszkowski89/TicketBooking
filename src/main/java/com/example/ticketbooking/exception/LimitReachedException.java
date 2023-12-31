package com.example.ticketbooking.exception;

public class LimitReachedException extends Exception {

    public LimitReachedException(String objects) {
        super(String.format("Maximum number of %s already created.", objects));
    }
}
