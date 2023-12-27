package com.splitwise.demo.dtos;

import com.splitwise.demo.models.ExpenseType;
import com.splitwise.demo.models.User;
import lombok.Data;

@Data
public class ExpenseUserRequestDto {
    int id;
    private ExpenseRequestDto expense;
    private double amount;
    private User user;
    private ExpenseType expenseType;
}
