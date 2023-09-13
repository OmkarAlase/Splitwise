package com.splitwise.demo.repositories;

import com.splitwise.demo.models.ExpenseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser,Integer> {
    @Query("select * from expense_user eu " +
            "inner join group_expense ge " +
            "eu.expense_id != ge.expense_id and eu.user_id = :id")
    public List<ExpenseUser> getAllNonGroupExpenseUser(@Param("id") int id);
}
