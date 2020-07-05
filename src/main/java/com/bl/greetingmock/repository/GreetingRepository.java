package com.bl.greetingmock.repository;

import com.bl.greetingmock.model.Greeting;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("greetingRepository")
public interface GreetingRepository extends CrudRepository<Greeting,Integer> {
}
