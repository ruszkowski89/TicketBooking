package com.example.ticketbooking.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomRepository<T> extends CrudRepository<T, Long> {

    @Override
    List<T> findAll();

}
