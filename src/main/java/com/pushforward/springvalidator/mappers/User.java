package com.pushforward.springvalidator.mappers;

//@AllArgsConstructor
//@NoArgsConstructor
//@Data
public record User(String name, String password, Address address, Contact contact) {
    record Address(String unit, Integer block) {
    }

    record Contact(Number home, Number office) {
        record Number(String mobile) {

        }
    }

}
