package com.pushforward.springvalidator;

import com.pushforward.springvalidator.controllers.Pet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class Runner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(RestClient.create()
                .post()
                .uri("http://localhost:8080/")
                .body(new Pet())
                .retrieve()
                .toBodilessEntity());
    }
}
