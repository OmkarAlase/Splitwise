package com.splitwise.demo.services;

import com.splitwise.demo.dtos.ExpenseDTO;
import com.splitwise.demo.models.Expense;
import com.splitwise.demo.models.ExpenseType;
import com.splitwise.demo.models.ExpenseUser;
import com.splitwise.demo.models.User;
import com.splitwise.demo.repositories.ExpenseRepository;
import com.splitwise.demo.utils.ExpenseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserService userService;
    @Override
    public Expense getExpense(int id) {
        return this.expenseRepository.findById(id).get();
    }

    @Override
    public List<Expense> getAllExpense() {
        return this.expenseRepository.findAll();
    }

    @Override
    @Transactional
    public Expense createExpense(ExpenseDTO dto){
        /*
            1. Convert ExpenseDTO to Expense - Done
            2. Save Expense to the DB - Done
            3. Update Group_Expense table.
         */
        Expense expense = ExpenseUtils.mapToExpense(dto);
        System.out.println("******** Expense " + expense);
        expense = this.expenseRepository.save(expense);
        return expense;
    }
}
