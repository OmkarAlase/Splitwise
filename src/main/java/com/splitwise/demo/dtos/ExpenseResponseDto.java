package com.splitwise.demo.dtos;

import com.splitwise.demo.models.Currency;
import com.splitwise.demo.models.ExpenseUser;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class ExpenseResponseDto {
    private int id;
    private double amount;
    private Date addedAt;
    private String description;
    private String proofUrl;
    private Currency currency;
    List<ExpenseUserResponseDto> expenseUsers;
}
