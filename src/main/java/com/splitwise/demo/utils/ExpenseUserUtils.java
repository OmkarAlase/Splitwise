package com.splitwise.demo.utils;

import com.splitwise.demo.dtos.ExpenseUserDTO;
import com.splitwise.demo.models.ExpenseUser;

import java.util.List;
import java.util.stream.Collectors;


public class ExpenseUserUtils {

    public static ExpenseUserDTO mapToExpenseUserDTO(ExpenseUser expenseUser){
        ExpenseUserDTO expenseUserDTO = new ExpenseUserDTO();
        expenseUserDTO.setExpenseType(expenseUser.getExpenseType());
        expenseUserDTO.setUser(expenseUser.getUser());
        expenseUserDTO.setAmount(expenseUser.getAmount());
        return expenseUserDTO;
    }

    public static List<ExpenseUserDTO> mapAllToExpenseUserDTO(List<ExpenseUser> expenseUserList){
        return expenseUserList.stream().map(expenseUser -> {
            return ExpenseUserUtils.mapToExpenseUserDTO(expenseUser);
        }).collect(Collectors.toList());
    }

    public static ExpenseUser mapToExpenseUser(ExpenseUserDTO expenseUserDTO){
        ExpenseUser expenseUser = new ExpenseUser();
        expenseUser.setExpenseType(expenseUserDTO.getExpenseType());
        expenseUser.setUser(expenseUserDTO.getUser());
        expenseUser.setAmount(expenseUserDTO.getAmount());

        return expenseUser;
    }

    public static List<ExpenseUser> mapAllToExpenseUser(List<ExpenseUserDTO> expenseUserList){
        return  expenseUserList.stream().map(expenseUserDTO -> {
            return ExpenseUserUtils.mapToExpenseUser(expenseUserDTO);
        }).collect(Collectors.toList());
    }
}
