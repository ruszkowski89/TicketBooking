package com.example.ticketbooking.model;

import com.example.ticketbooking.service.RowService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@RedisHash
@Data
@NoArgsConstructor
public class Room extends DBModel {

}
