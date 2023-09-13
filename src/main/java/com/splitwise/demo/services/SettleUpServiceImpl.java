package com.splitwise.demo.services;

import com.splitwise.demo.models.Expense;
import com.splitwise.demo.models.Transaction;
import com.splitwise.demo.models.User;
import com.splitwise.demo.repositories.GroupExpenseRepository;
import com.splitwise.demo.strategis.SettleUpStrategy;
import com.splitwise.demo.utils.ExpenseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class SettleUpServiceImpl implements SettleUpService{
    @Autowired
    private GroupExpenseRepository groupExpenseRepository;
    @Autowired
    private SettleUpStrategy settleUpStrategy;
    @Autowired
    private ExpenseUserService expenseUserService;
    @Override
    public List<Transaction> settleGroup(int groupId) {
        List<Expense> expenses = groupExpenseRepository.findByGroup_Id(groupId).stream().map(a -> a.getExpense()).collect(Collectors.toList());
        Map<User,Double> map = ExpenseUtils.aggregateExpenses(expenses);
        return settleUpStrategy.settleUp(map);
    }

    @Override
    public List<Transaction> settleUser(int userId) {
        List<Expense> expenses = this.expenseUserService.getExpenses(userId);
        Map<User,Double> map = ExpenseUtils.aggregateExpenses(expenses);
        return settleUpStrategy.settleUp(map);
    }
}
