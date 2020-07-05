package com.bl.greetingmock.service;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.model.Greeting;
import com.bl.greetingmock.repository.IGreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService implements IGreetingService {

    @Autowired
    private IGreetingRepository greetingRepository;

    @Override
    public Greeting add(GreetingDTO greetingDTO) {
        Greeting greeting = new Greeting(greetingDTO);
        greetingRepository.save(greeting);
        return greeting;
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
