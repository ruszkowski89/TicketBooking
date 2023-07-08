package com.example.ticketbooking.repository;

import com.example.ticketbooking.model.Screening;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ScreeningRepository extends CustomRepository<Screening> {

    List<Screening> findByRoomId(int roomId);

}
