package com.bl.greetingmock.service;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.exception.GreetingException;
import com.bl.greetingmock.model.Greeting;
import com.bl.greetingmock.repository.IGreetingRepository;
import com.bl.greetingmock.util.DateAndTimeFormatUtil;
import com.bl.greetingmock.util.GenerateUniqueId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class GreetingService implements IGreetingService {

    @Autowired
    IGreetingRepository greetingRepository;

    @Override
    public Greeting add(GreetingDTO greetingDTO) throws GreetingException {
        if (ObjectUtils.isEmpty(greetingDTO.getFirstName()) || ObjectUtils.isEmpty(greetingDTO.getLastName()))
                throw new GreetingException("Name may not be null");
        Greeting greeting = new Greeting(greetingDTO);
        greeting.setId(GenerateUniqueId.getUniqueId());
        greeting.setCreatedDate(DateAndTimeFormatUtil.currentDateAndTime());
        greeting.setUpdatedDate(DateAndTimeFormatUtil.currentDateAndTime());
        return greetingRepository.save(greeting);
    }

    @Override
    public Greeting getGreetingById(int id) throws GreetingException {
        if (id <= 0)
            throw new GreetingException("Zero or negative id not allowed");
        return greetingRepository.findById(id).get();
    }


    @Override
    public Greeting update(int id, GreetingDTO greetingDTO) throws GreetingException {
        if (id <= 0)
            throw new GreetingException("Zero or negative id not allowed");
        if (ObjectUtils.isEmpty(greetingDTO.getFirstName()) || ObjectUtils.isEmpty(greetingDTO.getLastName()))
            throw new GreetingException("Name may not be null");
        Greeting greeting = getGreetingById(id);
        greeting.setFirstName(greetingDTO.getFirstName());
        greeting.setLastName(greetingDTO.getLastName());
        greeting.setUpdatedDate(DateAndTimeFormatUtil.currentDateAndTime());
        return greetingRepository.save(greeting);
    }

    @Override
    public void delete(int id) throws GreetingException {
        if (id <= 0)
            throw new GreetingException("Zero or negative id not allowed");
        greetingRepository.deleteById(id);
    }

    @Override
    public List<Greeting> getAll() {
        return (List<Greeting>) greetingRepository.findAll();
    }
}
