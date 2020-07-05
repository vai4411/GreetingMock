package com.bl.greetingmock.service;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.model.Greeting;

import java.util.List;

public interface IGreetingService {
    Greeting add(GreetingDTO greetingDTO);
    Object getGreetingById(long id);
    Greeting update(int id, GreetingDTO greetingDTO);
    void delete(int id);
    List<Greeting> getAll();
}
