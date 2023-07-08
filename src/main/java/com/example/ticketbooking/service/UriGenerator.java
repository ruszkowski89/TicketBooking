package com.example.ticketbooking.service;

import com.example.ticketbooking.controller.ReservationController;
import lombok.experimental.UtilityClass;

import java.net.URI;
import java.nio.file.Path;

@UtilityClass
public class UriGenerator {
    static URI generateReservationConfirmUri(String reqPath, long id) {
        Path pathWithPlaceholder = Path.of(reqPath, ReservationController.CONFIRM_PATH);
        return URI.create(pathWithPlaceholder.toString().replace("{id}", String.valueOf(id)));
    }
}
