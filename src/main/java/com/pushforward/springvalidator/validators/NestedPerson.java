package com.pushforward.springvalidator.validators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NestedPerson {
    private Address address;

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static class Address {
        private String unit;
        private Integer block;
    }
}
