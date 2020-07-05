package com.bl.greetingmock.service;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.model.Greeting;
import com.bl.greetingmock.repository.GreetingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class GreetingServiceTest {

    @InjectMocks
    private GreetingService greetingService;

    @Mock
    private GreetingRepository greetingRepository;

//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//        ReflectionTestUtils.setField(greetingService, "greetingRepository", greetingRepository);
//    }

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
        Mockito.when(greetingRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(greeting));
        Greeting greeting1 = greetingService.getGreetingById(0);
        Assert.assertEquals(Optional.of(greeting).get(),greeting1);
    }

    @Test
    public void givenGreeting_WhenGreetingGetByIdAndUpdate_ThenReturnGreeting() {
        GreetingDTO greetingDTO = new GreetingDTO();
        greetingDTO.setFirstName("abc");
        greetingDTO.setLastName("def");
        Greeting greeting = new Greeting(greetingDTO);
        greeting.setFirstName("xyz");
        Mockito.when(greetingRepository.save(Mockito.any())).thenReturn(greeting);
        Mockito.when(greetingRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(greeting));
        Greeting greeting1 = greetingService.update(1,greetingDTO);
        Assert.assertEquals(greeting,greeting1);
    }

    @Test
    public void givenGreeting_WhenGetGreetingList_ThenReturnGreetingList() {
        List<Greeting> list = new ArrayList<Greeting>();
        list.add(new Greeting(new GreetingDTO("vaibhav","patil")));
        list.add(new Greeting(new GreetingDTO("abc","def")));
        Mockito.when(greetingRepository.findAll()).thenReturn(list);
        List list1 = greetingService.getAll();
        Assert.assertEquals(list,list1);
    }
}
