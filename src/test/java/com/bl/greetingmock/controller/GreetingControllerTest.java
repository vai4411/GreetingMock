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

import java.util.ArrayList;
import java.util.List;

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
        greetingDTO.setFirstName("abc");
        greetingDTO.setLastName("def");
        Greeting greeting = new Greeting(greetingDTO);
        greeting.setId(4);
        Mockito.when(greetingService.add(Mockito.any())).thenReturn(greeting);
        String greetingMessage = mapper.writeValueAsString(greeting);
        MvcResult result = mockMvc.perform(post("/greeting/add")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(greetingMessage))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(greetingMessage,content);
    }

    @Test
    public void givenGreeting_WhenGreetingAddPathIsWrong_ThenReturnHttpStatusNotFound() throws Exception {
        GreetingDTO greetingDTO = new GreetingDTO("vaibhav","patil");
        Greeting greeting = new Greeting(greetingDTO);
        String greetingMessage = mapper.writeValueAsString(greeting);
        mockMvc.perform(post("/greeting/adds")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(greetingMessage))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void givenGreeting_WhenGreetingAddMappingIsWrong_ThenReturnHttpStatusMethodNotAllowedRequest() throws Exception {
        GreetingDTO greetingDTO = new GreetingDTO("vaibhav","patil");
        Greeting greeting = new Greeting(greetingDTO);
        String greetingMessage = mapper.writeValueAsString(greeting);
        mockMvc.perform(get("/greeting/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(greetingMessage))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    @Test
    public void givenGreeting_WhenGreetingGetByIdSuccessfully_ThenReturnGreeting() throws Exception {
        GreetingDTO greetingDTO = new GreetingDTO("vaibhav","patil");
        Greeting greeting = new Greeting(greetingDTO);
        Mockito.when(greetingService.getGreetingById(Mockito.anyInt())).thenReturn(greeting);
        String greetingMessage = mapper.writeValueAsString(greeting);
        MvcResult result = mockMvc.perform(get("/greeting/display/1"))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(greetingMessage,content);
    }

    @Test
    public void givenGreeting_WhenGreetingGetByIdPathIsWrong_ThenReturnHttpStatusNotFound() throws Exception {
        mockMvc.perform(get("/greeting/displays/1"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void givenGreeting_WhenGreetingGetByIdMappingIsWrong_ThenReturnHttpStatusMethodNotAllowedRequest() throws Exception {
        mockMvc.perform(post("/greeting/display/1"))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    @Test
    public void givenGreeting_WhenGreetingGetByIdAndUpdate_ThenReturnGreeting() throws Exception {
        GreetingDTO greetingDTO = new GreetingDTO();
        greetingDTO.setFirstName("abc");
        greetingDTO.setLastName("def");
        Greeting greeting = new Greeting(greetingDTO);
        Mockito.when(greetingService.update(Mockito.anyInt(),Mockito.any())).thenReturn(greeting);
        String greetingMessage = mapper.writeValueAsString(greeting);
        MvcResult result = mockMvc.perform(put("/greeting/update/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(greetingMessage))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(greetingMessage,content);
    }

    @Test
    public void givenGreeting_WhenGreetingUpdateByIdPathIsWrong_ThenReturnHttpStatusNotFound() throws Exception {
        mockMvc.perform(get("/greeting/updates/1"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void givenGreeting_WhenGreetingUpdateByIdMappingIsWrong_ThenReturnHttpStatusMethodNotAllowedRequest() throws Exception {
        mockMvc.perform(post("/greeting/update/1"))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }


    @Test
    public void givenGreeting_WhenGreetingDeleteByIdSuccessFully_ThenReturnStatusOK() throws Exception {
        mockMvc.perform(delete("/greeting/delete/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void givenGreeting_WhenGreetingDeleteByIdPathIsWrong_ThenReturnHttpStatusNotFound() throws Exception {
        mockMvc.perform(get("/greeting/deletes/1"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void givenGreeting_WhenGreetingDeleteByIdMappingIsWrong_ThenReturnHttpStatusMethodNotAllowedRequest() throws Exception {
        mockMvc.perform(post("/greeting/delete/1"))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }


    @Test
    public void givenGreeting_WhenGetGreetingList_ThenReturnGreetingList() throws Exception {
        List<Greeting> list = new ArrayList<Greeting>();
        list.add(new Greeting(new GreetingDTO("vaibhav","patil")));
        list.add(new Greeting(new GreetingDTO("abc","def")));
        Mockito.when(greetingService.getAll()).thenReturn(list);
        String greetingMessage = mapper.writeValueAsString(list);
        MvcResult result = mockMvc.perform(get("/greeting/list")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(greetingMessage))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(greetingMessage,content);
    }

    @Test
    public void givenGreeting_WhenGreetingListPathIsWrong_ThenReturnHttpStatusNotFound() throws Exception {
        mockMvc.perform(get("/greeting/lists"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void givenGreeting_WhenGreetingListMappingIsWrong_ThenReturnHttpStatusMethodNotAllowedRequest() throws Exception {
        mockMvc.perform(post("/greeting/list"))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }
}