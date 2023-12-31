package com.splitwise.demo.utils;

import com.splitwise.demo.dtos.UserDto;
import com.splitwise.demo.models.User;

public class UserUtils {
    public static User mapToUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getName());
        user.setPhoneNo(userDto.getPhoneNo());
        return user;
    }

    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getUsername());
        userDto.setPhoneNo(user.getPhoneNo());
        return userDto;
    }
}
