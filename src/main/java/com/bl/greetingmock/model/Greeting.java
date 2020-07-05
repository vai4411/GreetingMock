package com.bl.greetingmock.model;

import com.bl.greetingmock.dto.GreetingDTO;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String createdDate;
    private String updatedDate;

    public Greeting(GreetingDTO greetingDTO) {
        this.firstName = greetingDTO.getFirstName();
        this.lastName = greetingDTO.getLastName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greeting greeting = (Greeting) o;
        return id == greeting.id &&
                Objects.equals(firstName, greeting.firstName) &&
                Objects.equals(lastName, greeting.lastName) &&
                Objects.equals(createdDate, greeting.createdDate) &&
                Objects.equals(updatedDate, greeting.updatedDate);
    }
}
