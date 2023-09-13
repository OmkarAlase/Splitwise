package com.splitwise.demo.strategis;

import com.splitwise.demo.models.Transaction;
import com.splitwise.demo.models.User;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
@Component
public class TwoSetsSettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> settleUp(Map<User, Double> map) {
        PriorityQueue<Pair<User,Double>> min = new PriorityQueue<>((a,b)->{
            return (int)(a.getSecond() - b.getSecond());
        });

        PriorityQueue<Pair<User,Double>> max = new PriorityQueue<>((a,b)->{
            return (int)(b.getSecond() - a.getSecond());
        });

        for (Map.Entry<User, Double> entry : map.entrySet()) {
            if(entry.getValue() > 0){
                max.add(Pair.of(entry.getKey(), entry.getValue()));
            } else {
                max.add(Pair.of(entry.getKey(), entry.getValue()));
            }
        }
        List<Transaction> transactions = new ArrayList<>();
        while(max.size() > 0 && min.size() > 0){
            Pair<User, Double> userToPay = min.poll();
            Pair<User, Double> userToGet = max.poll();

            double amountToBeTranseferred = userToGet.getSecond()  - userToPay.getSecond();
            Transaction transaction = new Transaction();
            transaction.setAmount(amountToBeTranseferred);
            transaction.setPaidBy(userToPay.getFirst());
            transaction.setPaidTo(userToGet.getFirst());
            transactions.add(transaction);

            if(userToPay.getSecond() + amountToBeTranseferred < 0){
                min.offer(Pair.of(userToPay.getFirst(),userToPay.getSecond() + amountToBeTranseferred));
            }

            if(userToGet.getSecond() - amountToBeTranseferred > 0){
                max.offer(Pair.of(userToGet.getFirst(),userToGet.getSecond() - amountToBeTranseferred));
            }
        }

        return transactions;
    }
}
