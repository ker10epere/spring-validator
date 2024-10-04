package com.pushforward.springvalidator.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
@Import(PetController.PrintAddTypeHeadersRequest.class)
class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void addPet() throws Exception {
        String body = new ObjectMapper().writeValueAsString(new PetController.Pet("Kira"));
        this.mockMvc.perform(
                        post("/pet")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void addType() throws Exception {
        String body = new ObjectMapper().writeValueAsString(new PetController.AddType(List.of("dog", "cat"), "Type Added"));
        this.mockMvc.perform(
                        post("/pet/types")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void addType_isLessThanTwoTypes() throws Exception {
        String body = new ObjectMapper().writeValueAsString(new PetController.AddType(List.of("dog"), null));
        this.mockMvc.perform(
                        post("/pet/types")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print()).andExpect(status().isBadRequest());
    }
}