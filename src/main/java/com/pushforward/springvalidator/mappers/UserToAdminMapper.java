package com.pushforward.springvalidator.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserToAdminMapper {
    Admin userToAdmin(User user);

    public static void main(String[] args) {
        UserToAdminMapper mapper = Mappers.getMapper(UserToAdminMapper.class);
        User user = new User(null, "123!@#", null, new User.Contact(new User.Contact.Number("+63 912345"), new User.Contact.Number(null)));
        System.out.println(mapper.userToAdmin(user));
    }
}
