package com.example.ticketBookingApp.service;

import com.example.ticketBookingApp.model.DBModel;
import com.example.ticketBookingApp.repository.CustomRepository;
import org.springframework.data.redis.core.RedisTemplate;

public class ParentService<T> {

    protected final CustomRepository<T> repo;
    private final RedisTemplate<String, Object> redisTemplate;


    public ParentService(CustomRepository<T> repo, RedisTemplate<String, Object> redisTemplate) {
        this.repo = repo;
        this.redisTemplate = redisTemplate;
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    public void setIdSequence(DBModel model) {
        var opsForValue = redisTemplate.opsForValue();
        model.setId(opsForValue.increment("sequence", 1));
    }

}
