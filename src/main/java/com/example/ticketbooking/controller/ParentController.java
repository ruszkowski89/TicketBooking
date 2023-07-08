package com.example.ticketbooking.controller;

import com.example.ticketbooking.service.ParentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class ParentController<E, T extends ParentService<E>> {
    protected final T service;

    public ParentController(T service) {
        this.service = service;
    }

    @DeleteMapping()
    public @ResponseBody void deleteAll() {
        service.deleteAll();
    }
}
