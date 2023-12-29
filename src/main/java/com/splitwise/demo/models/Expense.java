package com.splitwise.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Expense extends BaseModel{
    private double amount;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date addedAt;
    private String description;
    private String proofUrl;
    @Enumerated(value = EnumType.ORDINAL)
    private Currency currency;
    @Cascade(CascadeType.ALL)
    @OneToMany(mappedBy = "expense")
    List<ExpenseUser> expenseUsers;

    @PostPersist
    public void onCreate(){
        for (ExpenseUser expenseUser : expenseUsers) {
            if(Objects.isNull(expenseUser.getExpense())){
                expenseUser.setExpense(this);
            }
        }
    }
}
