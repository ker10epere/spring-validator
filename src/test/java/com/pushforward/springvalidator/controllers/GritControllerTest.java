package com.pushforward.springvalidator.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GritController.class)
class GritControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void printMessage() throws Exception {
        String body = new ObjectMapper().writeValueAsString(new GritController.GritMessageRequest("Push Forward"));
        this.mockMvc.perform(
                        post("/grit/")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void printMessage_isEmptyMessageBody() throws Exception {
        String body = new ObjectMapper().writeValueAsString(new GritController.GritMessageRequest(""));
        this.mockMvc.perform(
                        post("/grit/")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print()).andExpect(status().isBadRequest());
    }


    @Test
    void printMotivation() throws Exception {
        String body = new ObjectMapper().writeValueAsString(new GritController.GritMotivationRequest("Push Forward"));
        this.mockMvc.perform(
                        post("/grit/motivation")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print()).andExpect(status().isOk());
    }

}
