package com.pushforward.springvalidator.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@Slf4j
@ControllerAdvice(assignableTypes = GritController.class)
public class GritMessageRequestBodyAdvice extends RequestBodyAdviceAdapter {
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        boolean isSupported = GritController.GritMessageRequest.class.getTypeName().equals(targetType.getTypeName());
        log.info("isSupported | {}", isSupported);
        return isSupported;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        InputStream body = inputMessage.getBody();
        final byte[] requestBodyBytes = body.readAllBytes();

        System.out.println(new String(requestBodyBytes, StandardCharsets.UTF_8));
//        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "I caused an error");
        return new MappingJacksonInputMessage(new ByteArrayInputStream(requestBodyBytes), inputMessage.getHeaders()); //set the updated bodyStr
    }
}
