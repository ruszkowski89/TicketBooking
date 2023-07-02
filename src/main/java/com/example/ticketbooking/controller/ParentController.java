package com.example.ticketbooking.controller;

import com.example.ticketbooking.service.ParentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class ParentController<T> {
    protected final ParentService<T> parentService;

    public ParentController(ParentService<T> parentService) {
        this.parentService = parentService;
    }

    @DeleteMapping()
    public @ResponseBody void deleteAll() {
        parentService.deleteAll();
    }
}
