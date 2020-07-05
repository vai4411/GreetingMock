package com.bl.greetingmock.service;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.model.Greeting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService implements IGreetingService {

    @Override
    public Greeting add(GreetingDTO greetingDTO) {
        return null;
    }

    @Override
    public Greeting getGreetingById(int id) {
        return null;
    }

    @Override
    public Greeting update(int id, GreetingDTO greetingDTO) {
        return null;
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public List<Greeting> getAll() {
        return null;
    }
}
