package com.pushforward.springvalidator.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class NestedPersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NestedPerson.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        // NULL VALIDATION WORK
        ValidationUtils.rejectIfEmpty(e, "address", "field.required");

        // NOT POSSIBLE TO VALIDATE NESTED OBJECT USING SOLELY CURRENT VALIDATOR
        // MULTIPLE VALIDATORS NEEDED FOR NESTED VALIDATOR
//        ValidationUtils.rejectIfEmpty(e, "address.unit", "unit.empty");

        try {
            e.pushNestedPath("address");
            ValidationUtils.invokeValidator(new AddressValidator(), ((NestedPerson) obj).getAddress(), e);
        } finally {
            e.popNestedPath();
        }
//        Person p = (Person) obj;
//        if (p.getAge() == null) {
//            e.rejectValue("age", "null value");
//        } else if (p.getAge() < 0) {
//            e.rejectValue("age", "negativevalue");
//        } else if (p.getAge() > 110) {
//            e.rejectValue("age", "too.darn.old");
//        }
    }

    public static void main(String[] args) {
        NestedPersonValidator npv = new NestedPersonValidator();
        NestedPerson nestedPerson = new NestedPerson(new NestedPerson.Address());

        Errors simpleErrors = new BeanPropertyBindingResult(nestedPerson, "nestedPerson");
        npv.validate(nestedPerson, simpleErrors);

        System.out.println(simpleErrors.getAllErrors());
    }
}
