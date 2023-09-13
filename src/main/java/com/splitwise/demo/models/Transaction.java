package com.splitwise.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Transaction extends BaseModel{
    private double amount;
    @ManyToOne
    private User paidBy;
    @ManyToOne
    private User paidTo;
}
