package com.example.ticketBookingApp.repository;

import com.example.ticketBookingApp.model.Screening;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ScreeningRepository extends CustomRepository<Screening> {

    List<Screening> findByRoomId(int roomId);

}
