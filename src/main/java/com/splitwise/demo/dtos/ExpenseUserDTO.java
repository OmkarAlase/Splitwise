package com.splitwise.demo.dtos;

import com.splitwise.demo.models.Expense;
import com.splitwise.demo.models.ExpenseType;
import com.splitwise.demo.models.User;
import lombok.Data;

@Data
public class ExpenseUserDTO extends BaseModelDTO{
    private Expense expense;
    private double amount;
    private User user;
    private ExpenseType expenseType;
}
