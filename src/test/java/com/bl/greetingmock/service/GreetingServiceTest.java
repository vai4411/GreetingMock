package com.bl.greetingmock.service;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.exception.GreetingException;
import com.bl.greetingmock.model.Greeting;
import com.bl.greetingmock.repository.IGreetingRepository;
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
    private IGreetingRepository IGreetingRepository;

    @Test
    public void givenGreeting_WhenGreetingAddedSuccessfully_ThenReturnGreeting() throws GreetingException {
        GreetingDTO greetingDTO = new GreetingDTO();
        greetingDTO.setFirstName("abc");
        greetingDTO.setLastName("def");
        Greeting greeting = new Greeting(greetingDTO);
        Mockito.when(IGreetingRepository.save(Mockito.any())).thenReturn(greeting);
        Greeting greeting1 = greetingService.add(greetingDTO);
        Assert.assertEquals(greeting,greeting1);
    }

    @Test
    public void givenGreeting_WhenGreetingGetByIdSuccessfully_ThenReturnGreeting() throws GreetingException {
        GreetingDTO greetingDTO = new GreetingDTO();
        greetingDTO.setFirstName("abc");
        greetingDTO.setLastName("def");
        Greeting greeting = new Greeting(greetingDTO);
        Mockito.when(IGreetingRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(greeting));
        Greeting greeting1 = greetingService.getGreetingById(1);
        Assert.assertEquals(Optional.of(greeting).get(),greeting1);
    }

    @Test
    public void givenGreeting_WhenGreetingGetByIdAndUpdate_ThenReturnGreeting() throws GreetingException {
        GreetingDTO greetingDTO = new GreetingDTO();
        greetingDTO.setFirstName("abc");
        greetingDTO.setLastName("def");
        Greeting greeting = new Greeting(greetingDTO);
        greeting.setFirstName("xyz");
        Mockito.when(IGreetingRepository.save(Mockito.any())).thenReturn(greeting);
        Mockito.when(IGreetingRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(greeting));
        Greeting greeting1 = greetingService.update(1,greetingDTO);
        Assert.assertEquals(greeting,greeting1);
    }

    @Test
    public void givenGreeting_WhenGetGreetingList_ThenReturnGreetingList() {
        List<Greeting> list = new ArrayList<Greeting>();
        list.add(new Greeting(new GreetingDTO("vaibhav","patil")));
        list.add(new Greeting(new GreetingDTO("abc","def")));
        Mockito.when(IGreetingRepository.findAll()).thenReturn(list);
        List list1 = greetingService.getAll();
        Assert.assertEquals(list,list1);
    }

    @Test
    public void givenGreeting_WhenGreetingAddWithEmptyName_ThenThrowException() {
        GreetingDTO greetingDTO = new GreetingDTO();
        greetingDTO.setFirstName("");
        greetingDTO.setLastName("");
        try {
            greetingService.add(greetingDTO);
        } catch (GreetingException e) {
            Assert.assertEquals("Name may not be null",e.getMessage());
        }
    }

    @Test
    public void givenGreeting_WhenGreetingUpdateWithEmptyName_ThenThrowException() {
        GreetingDTO greetingDTO = new GreetingDTO();
        greetingDTO.setFirstName("");
        greetingDTO.setLastName("");
        try {
            greetingService.update(1,greetingDTO);
        } catch (GreetingException e) {
            Assert.assertEquals("Name may not be null",e.getMessage());
        }
    }

    @Test
    public void givenGreeting_WhenGreetingFindByNegativeId_ThenThrowException() {
        try {
            greetingService.getGreetingById(-1);
        } catch (GreetingException e) {
            Assert.assertEquals("Zero or negative id not allowed",e.getMessage());
        }
    }

    @Test
    public void givenGreeting_WhenGreetingDeleteByZeroId_ThenThrowException() {
        try {
            greetingService.getGreetingById(0);
        } catch (GreetingException e) {
            Assert.assertEquals("Zero or negative id not allowed",e.getMessage());
        }
    }
}
