package com.splitwise.demo.utils;

import com.splitwise.demo.models.Group;
import com.splitwise.demo.models.Transaction;
import com.splitwise.demo.models.User;
import com.splitwise.demo.repositories.GroupRepository;
import com.splitwise.demo.repositories.PendingSettlementRepo;
import com.splitwise.demo.services.GroupService;
import com.splitwise.demo.services.SettleUpService;
import com.splitwise.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsersPendingPreFetchUtil {
    @Autowired
    private UserService userService;
    @Autowired
    private SettleUpService settleUpService;
    @Autowired
    private GroupRepository groupRepository;

    public void preFetchAndPopulateAllThePendingDetails(){
        List<User> users = this.userService.getAllUsers();
        PendingSettlementRepo pendingSettlementRepo = PendingSettlementRepo.getInstance();
        for (User user : users) {
            List<Group> groups = this.groupRepository.getGroupsByUserId(user.getId());
            groups.addAll(this.groupRepository.getGroupsByAdminUserId(user.getId()));
            for (Group group : groups) {
                List<Transaction> transactions = this.settleUpService.settleGroup(group.getId());
                for (Transaction transaction : transactions) {
                    this.util(transaction,pendingSettlementRepo);
                }
            }
            List<Transaction> transactions = this.settleUpService.settleUser(user.getId());
            for (Transaction transaction : transactions) {
                this.util(transaction,pendingSettlementRepo);
            }
        }
    }

    public void util(Transaction transaction,PendingSettlementRepo pendingSettlementRepo){
        pendingSettlementRepo.putUser(transaction.getPaidTo().getId(),transaction.getPaidBy().getId(),transaction.getAmount());
    }
}
