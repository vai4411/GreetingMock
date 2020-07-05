package com.bl.greetingmock.service;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.model.Greeting;
import com.bl.greetingmock.repository.IGreetingRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class GreetingServiceTest {

    @InjectMocks
    private GreetingService greetingService;

    @Mock
    private IGreetingRepository greetingRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(greetingService, "greetingRepository", greetingRepository);
    }

    @Test
    public void givenGreeting_WhenGreetingAddedSuccessfully_ThenReturnGreeting() {
        GreetingDTO greetingDTO = new GreetingDTO();
        greetingDTO.setFirstName("abc");
        greetingDTO.setLastName("def");
        Greeting greeting = new Greeting(greetingDTO);
        Mockito.when(greetingRepository.save(Mockito.any())).thenReturn(greeting);
        Greeting greeting1 = greetingService.add(greetingDTO);
        Assert.assertEquals(greeting,greeting1);
    }

    @Test
    public void givenGreeting_WhenGreetingGetByIdSuccessfully_ThenReturnGreeting() {
        GreetingDTO greetingDTO = new GreetingDTO();
        greetingDTO.setFirstName("abc");
        greetingDTO.setLastName("def");
        Greeting greeting = new Greeting(greetingDTO);
        Mockito.when(greetingRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(greeting));
        Greeting greeting1 = greetingService.getGreetingById(0);
        Assert.assertEquals(Optional.of(greeting).get(),greeting1);
    }
}
