package com.splitwise.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class GroupExpense extends BaseModel{
    @ManyToOne
    private Expense expense;
    @ManyToOne
    private Group group;
}
