package com.splitwise.demo.dtos;

import com.splitwise.demo.models.Expense;
import com.splitwise.demo.models.ExpenseType;
import com.splitwise.demo.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ExpenseUserResponseDto {
    int id;
    private ExpenseResponseDto expense;
    private double amount;
    private User user;
    private ExpenseType expenseType;
}
