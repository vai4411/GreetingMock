package com.bl.greetingmock.service;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.model.Greeting;

public interface IGreetingService {
    Greeting add(GreetingDTO greetingDTO);
    Greeting getGreetingById(int id);
}
