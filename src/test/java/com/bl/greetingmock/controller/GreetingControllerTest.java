package com.bl.greetingmock.controller;

import com.bl.greetingmock.dto.GreetingDTO;
import com.bl.greetingmock.model.Greeting;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(MockitoJUnitRunner.class)
public class GreetingControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    private GreetingController greetingController;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(greetingController)
                .build();
    }

    @Test
    public void givenGreeting_WhenGreetingAddSuccessfully_ThenReturnStatusOK() throws Exception {
        GreetingDTO greetingDTO = new GreetingDTO();
        greetingDTO.setFirstName("vaibhav");
        greetingDTO.setLastName("patil");
        Greeting greeting = new Greeting(greetingDTO);
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
}