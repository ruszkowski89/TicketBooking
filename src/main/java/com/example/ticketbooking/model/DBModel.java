package com.example.ticketbooking.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class DBModel {
    @Id
    private long id;
}
