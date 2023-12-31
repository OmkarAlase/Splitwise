package com.splitwise.demo.controllers;

import com.splitwise.demo.dtos.LoginUserRequestDto;
import com.splitwise.demo.dtos.RegisterUserRequestDto;
import com.splitwise.demo.dtos.Response;
import com.splitwise.demo.dtos.UserDto;
import com.splitwise.demo.models.Transaction;
import com.splitwise.demo.models.User;
import com.splitwise.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/")
    public Response registerUser(@RequestBody RegisterUserRequestDto dto){
        try{
            User user = userService.registerUser(dto.getUsername(),dto.getPassword(),dto.getPhoneNo());
            return Response.getSuccessResponse("User Registered Successfully with ID - " + user.getId());
        }
        catch (Exception e){
            return Response.getFailureResponse(e.getMessage());
        }
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginUserRequestDto dto){
        try{
            userService.login(dto.getUsername(),dto.getPassword());
            return Response.getSuccessResponse("Login Success...");
        }
        catch(Exception e) {
            return Response.getFailureResponse(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable int id){
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
