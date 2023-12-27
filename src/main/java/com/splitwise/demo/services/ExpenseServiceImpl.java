package com.splitwise.demo.services;

import com.splitwise.demo.models.Expense;
import com.splitwise.demo.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseRepository expenseRepository;
    @Override
    public Expense getExpense(int id) {
        return this.expenseRepository.findById(id).get();
    }

    @Override
    public List<Expense> getAllExpense() {
        return this.expenseRepository.findAll();
    }

    @Override
    public Expense createExpense(Expense expense) {
        return this.expenseRepository.save(expense);
    }
}
