package com.splitwise.demo.services;

import com.splitwise.demo.dtos.ExpenseDTO;
import com.splitwise.demo.models.Expense;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ExpenseService {
    Expense getExpense(int id);
    List<Expense> getAllExpense();
    Expense createExpense(ExpenseDTO expense,int groupId);
}
