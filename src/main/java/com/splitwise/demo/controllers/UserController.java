package com.splitwise.demo.controllers;

import com.splitwise.demo.dtos.LoginUserRequestDto;
import com.splitwise.demo.dtos.RegisterUserRequestDto;
import com.splitwise.demo.dtos.Response;
import com.splitwise.demo.dtos.UserDto;
import com.splitwise.demo.models.User;
import com.splitwise.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    public Response registerUser(RegisterUserRequestDto dto){
        try{
            User user = userService.registerUser(dto.getUsername(),dto.getPassword(),dto.getPhoneNo());
            return Response.getSuccessResponse("User Registered Successfully with ID - " + user.getId());
        }
        catch (Exception e){
            return Response.getFailureResponse(e.getMessage());
        }
    }

    public Response login(LoginUserRequestDto dto){
        try{
            userService.login(dto.getUsername(),dto.getPassword());
            return Response.getSuccessResponse("Login Success...");
        }
        catch(Exception e) {
            return Response.getFailureResponse(e.getMessage());
        }
    }

    public UserDto findUserById(int id){
        try {
            User user = this.userService.getUserById(id);
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getUsername());
            userDto.setPhoneNo(user.getPhoneNo());
            return userDto;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
