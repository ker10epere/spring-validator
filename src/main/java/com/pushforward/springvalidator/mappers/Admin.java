package com.pushforward.springvalidator.mappers;

public record Admin(String name, String password, Address address, Contact contact) {
    record Address(String unit, Integer block) {
    }

    record Contact(Number home, Number office) {
        record Number(String mobile) {

        }
    }

}