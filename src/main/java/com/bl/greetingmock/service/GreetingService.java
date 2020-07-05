package com.bl.greetingmock.service;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.model.Greeting;
import com.bl.greetingmock.repository.IGreetingRepository;
import com.bl.greetingmock.util.DateAndTimeFormatUtil;
import com.bl.greetingmock.util.GenerateUniqueId;
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
        greeting.setId(GenerateUniqueId.getUniqueId());
        greeting.setCreatedDate(DateAndTimeFormatUtil.currentDateAndTime());
        greeting.setUpdatedDate(DateAndTimeFormatUtil.currentDateAndTime());
        greetingRepository.save(greeting);
        return greeting;
    }

    @Override
    public Greeting getGreetingById(long id) {
        return greetingRepository.findById(id).get();
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
