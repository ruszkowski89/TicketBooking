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

@BasePathAwareController(path = "rooms")
public class RoomController extends ParentController<Room> {

    private final RoomService service;

    @Autowired
    protected RoomController(RoomService service) {
        super(service);
        this.service = service;
    }

    @PostMapping
    public ResponseEntity post(@RequestBody RoomRequest req) {
        try {
            return ResponseEntity.ok(service.create(req.getRowsAmount(), req.getSeatsPerRow()));
        } catch (LimitReachedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body(e.getMessage());
        }
    }

    @Override
    @DeleteMapping()
    public @ResponseBody void deleteAll() {
        service.deleteAll();
    }

}
