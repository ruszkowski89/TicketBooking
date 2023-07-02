package com.example.ticketbooking.controller;

import com.example.ticketbooking.service.ParentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class ParentController<T> {
    protected final ParentService<T> service;

    public ParentController(ParentService<T> service) {
        this.service = service;
    }

    @DeleteMapping()
    public @ResponseBody void deleteAll() {
        service.deleteAll();
    }
}
