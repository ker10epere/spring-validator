package com.pushforward.springvalidator;

import com.pushforward.springvalidator.controllers.PetController;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@ConditionalOnMissingClass("org.springframework.boot.test.context.SpringBootTest")
public class Runner implements CommandLineRunner {
    @SneakyThrows
    @Override
    public void run(String... args) throws Exception {
        log.info(String.valueOf(RestClient.create()
                .post()
                .uri("http://localhost:8080/pet")
                .body(new PetController.Pet())
                .retrieve()
                .toBodilessEntity()));
    }
}
