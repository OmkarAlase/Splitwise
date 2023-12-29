package com.splitwise.demo.dtos;

import com.splitwise.demo.models.Currency;
import com.splitwise.demo.models.ExpenseUser;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class ExpenseDTO extends BaseModelDTO{
    private double amount;
    private Date addedAt;
    private String description;
    private String proofUrl;
    private Currency currency;
    List<ExpenseUserDTO> expenseUsers;
}
