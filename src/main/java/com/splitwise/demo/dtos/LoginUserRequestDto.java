package com.splitwise.demo.dtos;

import lombok.Data;

@Data
public class LoginUserRequestDto {
    private String username;
    private String password;
}
