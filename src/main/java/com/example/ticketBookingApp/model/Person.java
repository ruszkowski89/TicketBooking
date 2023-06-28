package com.example.ticketBookingApp.model;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Size(min = 3)
    @Pattern(regexp = "^[A-Z].*", message = "field must start with capital letter")
    private String name;
    @Size(min = 3)
    @Pattern(regexp = "[A-Z][a-z]*(-)*([A-Z][a-z]*)?", message = "surname must start with a capital letter and may consist of 2 parts separated with dash")
    private String surname;
}
