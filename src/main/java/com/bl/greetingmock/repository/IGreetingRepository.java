package com.bl.greetingmock.repository;

import com.bl.greetingmock.model.Greeting;
import org.springframework.data.repository.CrudRepository;

public interface IGreetingRepository extends CrudRepository<Greeting,Integer> {
}
