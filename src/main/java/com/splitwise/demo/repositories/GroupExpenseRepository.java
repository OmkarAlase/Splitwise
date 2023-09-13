package com.splitwise.demo.repositories;

import com.splitwise.demo.models.GroupExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GroupExpenseRepository extends JpaRepository<GroupExpense,Integer> {
    List<GroupExpense> findByGroup_Id(int groupId);
}
