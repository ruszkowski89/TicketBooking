package com.example.ticketBookingApp.service;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Service
public class ValidationService {
    private final Validator validator;

    @Autowired
    public ValidationService(LocalValidatorFactoryBean factory) {
        this.validator = factory.getValidator();
    }

    public void validate(Object o) {
        var violationSet = validator.validate(o);
        if (!violationSet.isEmpty()) {
            throw new ConstraintViolationException(violationSet);
        }
    }
}
