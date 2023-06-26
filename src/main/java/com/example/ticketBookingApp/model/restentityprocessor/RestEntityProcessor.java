package com.example.ticketBookingApp.model.restentityprocessor;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class RestEntityProcessor implements RepresentationModelProcessor<EntityModel<Object>> {

    @Override
    public EntityModel<Object> process(EntityModel<Object> model) {
        return EntityModel.of(model.getContent());
    }

}
