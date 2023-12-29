package com.splitwise.demo.controllers;

import com.splitwise.demo.dtos.ExpenseDTO;
import com.splitwise.demo.models.Expense;
import com.splitwise.demo.services.ExpenseService;
import com.splitwise.demo.utils.ExpenseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/")
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable int id){
        return null;
    }

    @PostMapping("/")
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO){
        Expense expense = this.expenseService.createExpense(expenseDTO);
        ExpenseDTO response = ExpenseUtils.mapToExpenseDTO(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
