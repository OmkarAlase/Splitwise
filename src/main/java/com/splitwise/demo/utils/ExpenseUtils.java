package com.splitwise.demo.utils;

import com.splitwise.demo.dtos.ExpenseRequestDto;
import com.splitwise.demo.dtos.ExpenseResponseDto;
import com.splitwise.demo.models.Expense;
import com.splitwise.demo.models.ExpenseType;
import com.splitwise.demo.models.ExpenseUser;
import com.splitwise.demo.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseUtils {
    public static Map<User,Double> aggregateExpenses(List<Expense> expenses){
        Map<User, Double> condensedExpenses = new HashMap<>();
        for (Expense expense : expenses) {
            for (ExpenseUser expenseUser : expense.getExpenseUsers()) {
                User user = expenseUser.getUser();
                double amountForThisExpense = expenseUser.getExpenseType().equals(ExpenseType.PAID) ? 1 * expenseUser.getAmount() : -1 * expenseUser.getAmount();
                condensedExpenses.put(user,  amountForThisExpense + condensedExpenses.getOrDefault(user, 0d));
            }
        }
        return condensedExpenses;
    }

    public static ExpenseResponseDto mapToExpenseResponseDto(Expense expense){
        ExpenseResponseDto dto = new ExpenseResponseDto();
        dto.setId(expense.getId());
        dto.setAmount(expense.getAmount());
        dto.setExpenseUsers(ExpenseUserUtils.mapAllToExpenseUserResponseDto(expense.getExpenseUsers()));
        dto.setCurrency(expense.getCurrency());
        dto.setDescription(expense.getDescription());
        dto.setProofUrl(expense.getProofUrl());
        return dto;
    }

    public static List<ExpenseResponseDto> mapAllToExpenseResponseDto(List<Expense> expenseList){
        List<ExpenseResponseDto> collect = expenseList.stream().map(expense -> {
            return ExpenseUtils.mapToExpenseResponseDto(expense);
        }).collect(Collectors.toList());

        return collect;
    }

    public static Expense mapToExpense(ExpenseRequestDto expense){
        Expense expense1 = new Expense();
        expense1.setId(expense.getId());
        expense1.setAmount(expense.getAmount());
        expense1.setCurrency(expense.getCurrency());
        expense1.setDescription(expense.getDescription());
        expense1.setProofUrl(expense.getProofUrl());
        return expense1;
    }

    public static List<Expense> mapAllToExpense(List<ExpenseRequestDto> expenseList){
        List<Expense> collect = expenseList.stream().map(expense -> {
            return ExpenseUtils.mapToExpense(expense);
        }).collect(Collectors.toList());
        return collect;
    }
}
