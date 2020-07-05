package com.bl.greetingmock.service;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.model.Greeting;
import com.bl.greetingmock.repository.GreetingRepository;
import com.bl.greetingmock.util.DateAndTimeFormatUtil;
import com.bl.greetingmock.util.GenerateUniqueId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService implements IGreetingService {

    @Autowired
    GreetingRepository greetingRepository;

    @Override
    public Greeting add(GreetingDTO greetingDTO) {
        Greeting greeting = new Greeting(greetingDTO);
        greeting.setId(GenerateUniqueId.getUniqueId());
        greeting.setCreatedDate(DateAndTimeFormatUtil.currentDateAndTime());
        greeting.setUpdatedDate(DateAndTimeFormatUtil.currentDateAndTime());
        return greetingRepository.save(greeting);
    }

    @Override
    public Greeting getGreetingById(int id) {
        return greetingRepository.findById(id).get();
    }


    @Override
    public Greeting update(int id, GreetingDTO greetingDTO) {
        Greeting greeting = getGreetingById(id);
        greeting.setFirstName(greetingDTO.getFirstName());
        greeting.setLastName(greetingDTO.getLastName());
        greeting.setUpdatedDate(DateAndTimeFormatUtil.currentDateAndTime());
        return greetingRepository.save(greeting);
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public List<Greeting> getAll() {
        return (List<Greeting>) greetingRepository.findAll();
    }
}
