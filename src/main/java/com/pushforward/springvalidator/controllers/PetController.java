package com.pushforward.springvalidator.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/pet")
public class PetController {
    private final PrintAddTypeHeadersRequest printAddTypeHeadersRequest;

    @PostMapping
    public void addPet(@RequestBody Pet pet) {
        log.info(String.valueOf(pet));
    }

    @PostMapping("/types")
    public void addType(@Valid @RequestBody AddType addType) throws IOException {
        log.info(String.valueOf(addType));
        printAddTypeHeadersRequest.printHeaders();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class AddType {
        private List<String> types;
        @NotEmpty
        private String message;

        @AssertTrue(message = "Types is less than 2")
        /*
        is<Method> name is important to validate groups.

        Without [is] prefix, this validation will not run.
        */
        private boolean isNice() {
            log.info("AddType.isValid");
            return types != null && types.size() >= 2;
        }
    }

    @Slf4j
    @Component
    public static class PrintAddTypeHeadersRequest {
        public void printHeaders() throws IOException {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            List<String> headerNames = Collections.list(request.getHeaderNames());
            Map<String, String> headers = headerNames.stream()
                    .map(name -> Map.entry(name, request.getHeader(name)))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            log.info("Print HeaderNames | {}", headerNames);
            log.info("Print Headers | {}", headers);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pet {
        private String name;
    }

}
