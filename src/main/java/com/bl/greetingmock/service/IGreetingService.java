package com.bl.greetingmock.service;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.exception.GreetingException;
import com.bl.greetingmock.model.Greeting;

import java.util.List;

public interface IGreetingService {
    Greeting add(GreetingDTO greetingDTO) throws GreetingException;
    Object getGreetingById(int id) throws GreetingException;
    Greeting update(int id, GreetingDTO greetingDTO) throws GreetingException;
    void delete(int id) throws GreetingException;
    List<Greeting> getAll();
}
