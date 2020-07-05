package com.bl.greetingmock.controller;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.exception.GreetingException;
import com.bl.greetingmock.model.Greeting;
import com.bl.greetingmock.service.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    IGreetingService greetingService;

    @PostMapping(path = "/add")
    public Greeting add(@RequestBody GreetingDTO greetingDTO) throws GreetingException {
        return greetingService.add(greetingDTO);
    }

    @GetMapping("/display/{id}")
    public Greeting getGreeting(@PathVariable int id) throws GreetingException {
        return (Greeting) greetingService.getGreetingById(id);
    }

    @PutMapping(path = "/update/{id}")
    public Greeting update(@PathVariable int id,@RequestBody GreetingDTO greetingDTO) throws GreetingException {
        return greetingService.update(id,greetingDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) throws GreetingException {
        greetingService.delete(id);
    }

    @GetMapping("/list")
    public List<Greeting> getAll() {
        return greetingService.getAll();
    }
}
