package com.example.ticketbooking.config;

import com.example.ticketbooking.model.Movie;
import com.example.ticketbooking.model.Reservation;
import com.example.ticketbooking.model.Room;
import com.example.ticketbooking.model.Screening;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepositoryRestConfigurerConfig {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return RepositoryRestConfigurer.withConfig(config -> {
            config.exposeIdsFor(Room.class);
            config.exposeIdsFor(Movie.class);
            config.exposeIdsFor(Screening.class);
            config.exposeIdsFor(Reservation.class);
        });
    }

}
