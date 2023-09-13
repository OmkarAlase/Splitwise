package com.splitwise.demo.dtos;

import com.splitwise.demo.models.Transaction;
import lombok.Data;

import java.util.List;
@Data
public class SettleUpGroupResponseDto {
    private Response response;
    private List<Transaction> transactions;
}
