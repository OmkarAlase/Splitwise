package com.splitwise.demo.dtos;

import com.splitwise.demo.models.Currency;
import com.splitwise.demo.models.ExpenseUser;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.List;
@Data
public class ExpenseRequestDto {
    private int id;
    private double amount;
    private Date addedAt;
    private String description;
    private String proofUrl;
    private Currency currency;
    List<Pair<Integer,Double>> expenseUsers;

}
