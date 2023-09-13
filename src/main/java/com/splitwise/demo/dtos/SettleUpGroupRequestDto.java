package com.splitwise.demo.dtos;

import lombok.Data;

@Data
public class SettleUpGroupRequestDto {
    private int groupId;
    private String description;
}
