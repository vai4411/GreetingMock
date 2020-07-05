package com.bl.greetingmock.dto;

import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;

public class GreetingDTO {

    private String firstName;
    private String lastName;

    public GreetingDTO() {
    }

    public GreetingDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
