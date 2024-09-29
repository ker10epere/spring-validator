package com.pushforward.springvalidator.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserToAdminMapperComponent {
    Admin userToAdmin(User user);

}
