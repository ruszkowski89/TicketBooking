package com.example.ticketBookingApp.controller;

import com.example.ticketBookingApp.exception.LimitReachedException;
import com.example.ticketBookingApp.model.Room;
import com.example.ticketBookingApp.model.request.RoomRequest;
import com.example.ticketBookingApp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@BasePathAwareController(path = "rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody RoomRequest req) {
        try {
            return new ResponseEntity<>(roomService.addRoom(req.getRowsAmount(), req.getSeatsPerRow()), HttpStatus.OK);
        } catch (LimitReachedException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Maximum number of rooms already created.");
        }
    }

    @DeleteMapping()
    public @ResponseBody void deleteAll() {
        roomService.deleteAll();
    }
}
