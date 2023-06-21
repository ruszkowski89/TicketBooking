package com.example.ticketBookingApp.model.restentityprocessor;

import com.example.ticketBookingApp.model.Room;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class RestEntityProcessor implements RepresentationModelProcessor<EntityModel<Room>> {

    @Override
    public EntityModel<Room> process(EntityModel<Room> model) {
        return EntityModel.of(model.getContent());
    }

}
