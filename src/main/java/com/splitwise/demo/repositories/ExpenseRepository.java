package com.splitwise.demo.repositories;

import com.splitwise.demo.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {

}
