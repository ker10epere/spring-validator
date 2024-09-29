package com.pushforward.springvalidator.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.SimpleErrors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        Person p = (Person) obj;
        if (p.getAge() == null) {
            e.rejectValue("age", "null value");
        } else if (p.getAge() < 0) {
            e.rejectValue("age", "negativevalue");
        } else if (p.getAge() > 110) {
            e.rejectValue("age", "too.darn.old");
        }
    }

    public static void main(String[] args) {
        Person ker = new Person("Ker", -1);
        PersonValidator pv = new PersonValidator();
        SimpleErrors se = new SimpleErrors(ker, "ker");
        pv.validate(ker, se);
        System.out.println(se);
        System.out.println(se.getFieldErrors());
        System.out.println(se.getGlobalErrors());
        System.out.println(se.getAllErrors());
    }
}
