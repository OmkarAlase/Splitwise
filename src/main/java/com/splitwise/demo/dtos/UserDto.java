package com.splitwise.demo.dtos;

import lombok.Data;

@Data
public class UserDto extends BaseModelDTO{
    private int id;
    private String name;
    private String phoneNo;
}
