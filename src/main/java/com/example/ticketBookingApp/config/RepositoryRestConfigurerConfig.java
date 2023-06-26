package com.example.ticketBookingApp.config;

import com.example.ticketBookingApp.model.Movie;
import com.example.ticketBookingApp.model.Reservation;
import com.example.ticketBookingApp.model.Room;
import com.example.ticketBookingApp.model.Screening;
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
