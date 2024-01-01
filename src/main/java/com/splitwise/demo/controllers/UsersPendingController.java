package com.splitwise.demo.controllers;

import com.splitwise.demo.repositories.PendingSettlementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/pending")
public class UsersPendingController {
    private PendingSettlementRepo pendingSettlementRepo;

    public UsersPendingController(){
        this.pendingSettlementRepo = PendingSettlementRepo.getInstance();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<Integer,Double>> getUsersPending(@PathVariable int userId){
        Map<Integer, Double> usersAllPending = this.pendingSettlementRepo.getUsersAllPending(userId);
        return ResponseEntity.ok(usersAllPending);
    }
    @GetMapping("/{userIdA}/{userIdB}")
    public ResponseEntity<Double> getPendingBetweenUsers(@PathVariable("userIdA") int userIdA,@PathVariable("userIdB") int userIdB){
        double usersPending = this.pendingSettlementRepo.getUsersPending(userIdA, userIdB);
        return ResponseEntity.ok(usersPending);
    }

}
