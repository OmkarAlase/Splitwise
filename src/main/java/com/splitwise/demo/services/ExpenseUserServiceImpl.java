package com.splitwise.demo.services;

import com.splitwise.demo.models.Expense;
import com.splitwise.demo.models.ExpenseUser;
import com.splitwise.demo.repositories.ExpenseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ExpenseUserServiceImpl implements ExpenseUserService{
    @Autowired
    private ExpenseUserRepository expenseUserRepository;
    @Override
    public List<Expense> getExpenses(int id) {
        List<ExpenseUser> expenseUsers = this.expenseUserRepository.getAllNonGroupExpenseUser(id);
        List<Expense> expenses = new ArrayList<>();
        for (ExpenseUser expenseUser : expenseUsers) {
            expenses.add(expenseUser.getExpense());
        }
        return expenses;
    }

    @Override
    public ExpenseUser getExpenseUser(int id) {
        return null;
    }


}
