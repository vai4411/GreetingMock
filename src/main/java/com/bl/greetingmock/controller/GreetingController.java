package com.bl.greetingmock.controller;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.model.Greeting;
import com.bl.greetingmock.service.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    IGreetingService greetingService;

    @PostMapping(path = "/add")
    public Greeting add(@RequestBody GreetingDTO greetingDTO) {
        return greetingService.add(greetingDTO);
    }

    @GetMapping("/display/{id}")
    public Greeting getGreeting(@PathVariable int id) {
        return greetingService.getGreetingById(id);
    }
}
