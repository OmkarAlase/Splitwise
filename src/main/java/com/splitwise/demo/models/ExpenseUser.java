package com.splitwise.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ExpenseUser extends BaseModel {
    @ManyToOne
    private Expense expense;
    private double amount;
    @ManyToOne
    private User user;
    @Enumerated(value = EnumType.ORDINAL)
    private ExpenseType expenseType;
}
