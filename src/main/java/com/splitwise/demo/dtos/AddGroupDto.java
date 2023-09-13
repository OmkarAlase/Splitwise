package com.splitwise.demo.dtos;

import lombok.Data;

import java.util.List;
@Data
public class AddGroupDto {
    private String name;
    private String description;
    private List<Integer> users;
    private List<Integer> admins;
}
