package com.splitwise.demo.utils;

import com.splitwise.demo.dtos.ExpenseDTO;
import com.splitwise.demo.dtos.ExpenseUserDTO;
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

    public static ExpenseDTO mapToExpenseDTO(Expense expense){
        ExpenseDTO expenseDTO = new ExpenseDTO();
        List<ExpenseUserDTO> expenseUserDTOS = ExpenseUserUtils.mapAllToExpenseUserDTO(expense.getExpenseUsers());
        expenseDTO.setExpenseUsers(expenseUserDTOS);
        expenseDTO.setAmount(expense.getAmount());
        expenseDTO.setCurrency(expense.getCurrency());
        expenseDTO.setDescription(expense.getDescription());
        expenseDTO.setAddedAt(expense.getAddedAt());
        expenseDTO.setProofUrl(expense.getProofUrl());
        expenseDTO.setId(expense.getId());
        return expenseDTO;
    }

    public static List<ExpenseDTO> mapAllToExpenseDTO(List<Expense> expenseList){
        return expenseList.stream().map(expense -> {
            return ExpenseUtils.mapToExpenseDTO(expense);
        }).collect(Collectors.toList());
    }

    public static Expense mapToExpense(ExpenseDTO expenseDTO){
        Expense expense = new Expense();
        List<ExpenseUser> expenseUser = ExpenseUserUtils.mapAllToExpenseUser(expenseDTO.getExpenseUsers());
        expense.setExpenseUsers(expenseUser);
        expense.setAmount(expenseDTO.getAmount());
        expense.setCurrency(expenseDTO.getCurrency());
        expense.setDescription(expenseDTO.getDescription());
        expense.setAddedAt(expenseDTO.getAddedAt());
        expense.setProofUrl(expenseDTO.getProofUrl());
        expense.setId(expenseDTO.getId());
        return expense;
    }

    public static List<Expense> mapAllToExpense(List<ExpenseDTO> expenseList){
        List<Expense> collect = expenseList.stream().map(expense -> {
            return ExpenseUtils.mapToExpense(expense);
        }).collect(Collectors.toList());
        return collect;
    }
}
