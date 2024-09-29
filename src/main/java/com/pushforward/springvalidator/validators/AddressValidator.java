package com.pushforward.springvalidator.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddressValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NestedPerson.Address.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        // NULL VALIDATION WORK
        ValidationUtils.rejectIfEmpty(e, "unit", "field.required");
        ValidationUtils.rejectIfEmpty(e, "block", "field.required");
    }
}
