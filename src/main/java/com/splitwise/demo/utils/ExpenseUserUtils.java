package com.splitwise.demo.utils;

import com.splitwise.demo.dtos.ExpenseUserRequestDto;
import com.splitwise.demo.dtos.ExpenseUserResponseDto;
import com.splitwise.demo.models.ExpenseUser;

import java.util.List;
import java.util.stream.Collectors;

public class ExpenseUserUtils {

    public static ExpenseUserResponseDto mapToExpenseUserResponseDto(ExpenseUser expenseUser){
        ExpenseUserResponseDto dto = new ExpenseUserResponseDto();
        dto.setId(expenseUser.getId());
        dto.setExpense(ExpenseUtils.mapToExpenseResponseDto(expenseUser.getExpense()));
        dto.setUser(expenseUser.getUser());
        dto.setExpenseType(expenseUser.getExpenseType());
        dto.setAmount(expenseUser.getAmount());
        return dto;
    }

    public static List<ExpenseUserResponseDto> mapAllToExpenseUserResponseDto(List<ExpenseUser> expenseUserList){
        List<ExpenseUserResponseDto> collect = expenseUserList.stream().map(expenseUser -> {
            return ExpenseUserUtils.mapToExpenseUserResponseDto(expenseUser);
        }).collect(Collectors.toList());
        return  collect;
    }


    public static ExpenseUser mapToExpenseUser(ExpenseUserRequestDto expenseUser){
        ExpenseUser eu = new ExpenseUser();
        eu.setId(expenseUser.getId());
        eu.setExpense(ExpenseUtils.mapToExpense(expenseUser.getExpense()));
        eu.setUser(expenseUser.getUser());
        eu.setExpenseType(expenseUser.getExpenseType());
        eu.setAmount(expenseUser.getAmount());
        return eu;
    }

    public static List<ExpenseUser> mapAllToExpenseUser(List<ExpenseUserRequestDto> expenseUserList){
        List<ExpenseUser> collect = expenseUserList.stream().map(expenseUser -> {
            return ExpenseUserUtils.mapToExpenseUser(expenseUser);
        }).collect(Collectors.toList());
        return  collect;
    }
}
