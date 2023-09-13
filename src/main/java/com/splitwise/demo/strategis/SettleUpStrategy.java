package com.splitwise.demo.strategis;

import com.splitwise.demo.models.Transaction;
import com.splitwise.demo.models.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface SettleUpStrategy {
    public List<Transaction> settleUp(Map<User,Double> map);
}
