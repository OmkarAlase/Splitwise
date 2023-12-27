package com.splitwise.demo.services;

import com.splitwise.demo.models.Expense;
import com.splitwise.demo.models.ExpenseUser;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ExpenseUserService {
    public List<Expense> getExpenses(int id);
    public ExpenseUser getExpenseUser(int id);
}
