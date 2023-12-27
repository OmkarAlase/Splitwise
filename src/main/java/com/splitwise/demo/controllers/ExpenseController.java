package com.splitwise.demo.controllers;

import com.splitwise.demo.dtos.ExpenseRequestDto;
import com.splitwise.demo.dtos.ExpenseResponseDto;
import com.splitwise.demo.dtos.ExpenseUserResponseDto;
import com.splitwise.demo.exceptions.UserException;
import com.splitwise.demo.models.Expense;
import com.splitwise.demo.models.ExpenseType;
import com.splitwise.demo.models.ExpenseUser;
import com.splitwise.demo.models.User;
import com.splitwise.demo.services.ExpenseService;
import com.splitwise.demo.services.UserService;
import com.splitwise.demo.utils.ExpenseUserUtils;
import com.splitwise.demo.utils.ExpenseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public ResponseEntity<List<ExpenseResponseDto>> getAllExpenses(){
        List<Expense> expenseList = this.expenseService.getAllExpense();
        List<ExpenseResponseDto> responseDtos = ExpenseUtils.mapAllToExpenseResponseDto(expenseList);
        return ResponseEntity.status(HttpStatus.FOUND).body(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> getExpenseById(@PathVariable int id){
        Expense expense = this.expenseService.getExpense(id);
        ExpenseResponseDto expenseResponseDto = ExpenseUtils.mapToExpenseResponseDto(expense);
        return ResponseEntity.status(HttpStatus.FOUND).body(expenseResponseDto);
    }

    @PostMapping("/")
    public ResponseEntity<ExpenseResponseDto> createExpense(@RequestBody ExpenseRequestDto expenseRequestDto) throws RuntimeException{
        Expense expense = ExpenseUtils.mapToExpense(expenseRequestDto);
        // First create expense then will get expense id -> This expense id will be used for expense - user mapping
        final Expense res = this.expenseService.createExpense(expense); // Here we have an expense id
        // second create ExpenseUser --> for this will pass amount + expense id as a input;
        List<ExpenseUser> collect = expenseRequestDto.getExpenseUsers().stream().map(user -> {
            int userId = user.getFirst();
            double amount = user.getSecond();
            ExpenseType expenseType = amount > 0 ? ExpenseType.PAID : ExpenseType.OWED;
            User userModel = null;
            try {
                userModel = this.userService.getUserById(userId);
            } catch (UserException e) {
                throw new RuntimeException(e);
            }
            ExpenseUser expenseUser = new ExpenseUser();
            expenseUser.setExpenseType(expenseType);
            expenseUser.setUser(userModel);
            expenseUser.setAmount(amount);
            expenseUser.setExpense(res);
            return expenseUser;
        }).collect(Collectors.toList());
        List<ExpenseUserResponseDto> toRespond = ExpenseUserUtils.mapAllToExpenseUserResponseDto(collect);
        res.setExpenseUsers(collect);
        return ResponseEntity.status(HttpStatus.CREATED).body(ExpenseUtils.mapToExpenseResponseDto(res));
    }
}
