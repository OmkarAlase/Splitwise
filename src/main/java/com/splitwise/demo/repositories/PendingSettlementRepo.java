package com.splitwise.demo.repositories;

import com.splitwise.demo.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This is singleton class we will create only one object of it
public class PendingSettlementRepo {
    private Map<Integer, Map<Integer,Double>> map;
    private static PendingSettlementRepo pendingSettlementRepo = null;
    private PendingSettlementRepo(){
        this.map = new HashMap<>();
    }
    public static PendingSettlementRepo getInstance(){
        if(pendingSettlementRepo == null){
            synchronized (PendingSettlementRepo.class){
                if(pendingSettlementRepo == null){
                    pendingSettlementRepo = new PendingSettlementRepo();
                }
            }
        }
        return pendingSettlementRepo;
    }


    public void putUser(int from,int to,double amount){
        map.putIfAbsent(from,new HashMap<>());
        Map<Integer,Double> mp = new HashMap<>();
        mp.put(to,amount);
        map.put(from,mp);
    }

    public Map<Integer,Double> getUsersAllPending(int userId){
        return map.getOrDefault(userId,null);
    }

    public double getUsersPending(int userIdA,int userIdB){
        if(!map.containsKey(userIdA)) return 0.0;
        return map.get(userIdA).getOrDefault(userIdB,0.0);
    }
}
