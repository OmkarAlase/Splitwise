package com.splitwise.demo.services;

import com.splitwise.demo.models.GroupExpense;
import com.splitwise.demo.repositories.GroupExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupExpenseServiceImpl implements GroupExpenseService{
    @Autowired
    private GroupExpenseRepository groupExpenseRepository;
    @Override
    public GroupExpense createGroupExpense(GroupExpense groupExpense) {
        return this.groupExpenseRepository.save(groupExpense);
    }
}
