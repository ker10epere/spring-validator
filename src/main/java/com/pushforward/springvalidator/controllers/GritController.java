package com.pushforward.springvalidator.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/grit")
public class GritController {

    @PostMapping("/")
    public void printMessage(@Valid @RequestBody GritMessageRequest request) {
        log.info("GritMessageRequest | {}", request);
    }

    @PostMapping("/motivation")
    public void printMotivation(@Valid @RequestBody GritMotivationRequest request) {
        log.info("GritMotivationRequest | {}", request);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GritMessageRequest {
        @NotEmpty
        private String message;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GritMotivationRequest {
        @NotEmpty
        private String message;
    }
}
