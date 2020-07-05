package com.bl.greetingmock.controller;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.model.Greeting;
import com.bl.greetingmock.service.IGreetingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(MockitoJUnitRunner.class)
public class GreetingControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    private GreetingController greetingController;

    @Mock
    private IGreetingService greetingService;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(greetingController, "greetingService", greetingService);
        mockMvc = MockMvcBuilders.standaloneSetup(greetingController)
                .build();
    }

    @Test
    public void givenGreeting_WhenGreetingAddedSuccessfully_ThenReturnGreeting() throws Exception {
        GreetingDTO greetingDTO = new GreetingDTO();
        greetingDTO.setFirstName("vaibhav");
        greetingDTO.setLastName("patil");
        Greeting greeting = new Greeting(greetingDTO);
        Mockito.when(greetingService.add(Mockito.any())).thenReturn(greeting);
        String greetingMessage = mapper.writeValueAsString(greeting);
        MvcResult result = mockMvc.perform(post("/greeting/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(greetingMessage))
                        .andExpect(status().isOk())
                        .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(greetingMessage,content);
    }

    @Test
    public void givenGreeting_WhenGreetingAddPathIsWrong_ThenReturnHttpStatusBadRequest() throws Exception {
        GreetingDTO greetingDTO = new GreetingDTO();
        greetingDTO.setFirstName("vaibhav");
        greetingDTO.setLastName("patil");
        Greeting greeting = new Greeting(greetingDTO);
        String greetingMessage = mapper.writeValueAsString(greeting);
        mockMvc.perform(post("/greeting/adds")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(greetingMessage))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void givenGreeting_WhenGreetingGetById_ThenReturnGreeting() throws Exception {
        GreetingDTO greetingDTO = new GreetingDTO();
        greetingDTO.setFirstName("vaibhav");
        greetingDTO.setLastName("patil");
        Greeting greeting = new Greeting(greetingDTO);
        Mockito.when(greetingService.getGreetingById(Mockito.anyInt())).thenReturn(greeting);
        String greetingMessage = mapper.writeValueAsString(greeting);
        MvcResult result = mockMvc.perform(get("/greeting/display/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(greetingMessage,content);
    }

    @Test
    public void givenGreeting_WhenGreetingGetByIdAndUpdate_ThenReturnGreeting() throws Exception {
        GreetingDTO greetingDTO = new GreetingDTO();
        greetingDTO.setFirstName("vaibhav");
        greetingDTO.setLastName("patil");
        Greeting greeting = new Greeting(greetingDTO);
        greeting.setFirstName("abc");
        Mockito.when(greetingService.update(Mockito.anyInt(),Mockito.any())).thenReturn(greeting);
        String greetingMessage = mapper.writeValueAsString(greeting);
        MvcResult result = mockMvc.perform(put("/greeting/update/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(greetingMessage))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(greetingMessage,content);
    }
}