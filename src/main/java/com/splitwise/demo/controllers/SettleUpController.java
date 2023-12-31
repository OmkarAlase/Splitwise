package com.splitwise.demo.controllers;

import com.splitwise.demo.dtos.Response;
import com.splitwise.demo.dtos.SettleUpGroupRequestDto;
import com.splitwise.demo.dtos.SettleUpGroupResponseDto;
import com.splitwise.demo.dtos.SettleUpUserResponseDto;
import com.splitwise.demo.models.Transaction;
import com.splitwise.demo.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/settle")
public class SettleUpController {
    @Autowired
    private SettleUpService settleUpService;
    @PutMapping("/groupSettle")
    public SettleUpGroupResponseDto settleGroup(@RequestBody SettleUpGroupRequestDto dto){
        List<Transaction> transactions = settleUpService.settleGroup(dto.getGroupId());
        Response response = Response.getSuccessResponse("Settled up successfully.");
        SettleUpGroupResponseDto res = new SettleUpGroupResponseDto();
        res.setResponse(response);
        res.setTransactions(transactions);
        return res;
    }

    @PutMapping("/userSettle")
    public SettleUpUserResponseDto settleUser(int userId){
        SettleUpUserResponseDto dto = new SettleUpUserResponseDto();
        try {
            List<Transaction> transactions = this.settleUpService.settleUser(userId);
            dto.setResponse(Response.getSuccessResponse("Settled up successfully.."));
            dto.setTransactions(transactions);
            return dto;
        }
        catch (Exception exception){
            dto.setResponse(Response.getFailureResponse("Settle up failed reason , - " + exception.getMessage()));
            dto.setTransactions(null);
            return dto;
        }
    }

    @GetMapping("/pending/{userIdA}/{userIdB}")
    public ResponseEntity<Double> getUserWisePendingAmount(@PathVariable("userIdA") int userIdA,@PathVariable("userIdB") int userIdB){
        return null;
    }
}
