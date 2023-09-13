package com.splitwise.demo.controllers;

import com.splitwise.demo.dtos.Response;
import com.splitwise.demo.dtos.SettleUpGroupRequestDto;
import com.splitwise.demo.dtos.SettleUpGroupResponseDto;
import com.splitwise.demo.dtos.SettleUpUserResponseDto;
import com.splitwise.demo.models.Transaction;
import com.splitwise.demo.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class SettleUpController {
    @Autowired
    private SettleUpService settleUpService;
    public SettleUpGroupResponseDto settleGroup(SettleUpGroupRequestDto dto){
        List<Transaction> transactions = settleUpService.settleGroup(dto.getGroupId());
        Response response = Response.getSuccessResponse("Settled up successfully.");
        SettleUpGroupResponseDto res = new SettleUpGroupResponseDto();
        res.setResponse(response);
        res.setTransactions(transactions);
        return res;
    }

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
}
