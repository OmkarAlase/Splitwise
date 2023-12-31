package com.splitwise.demo.utils;

import com.splitwise.demo.dtos.AddGroupDto;
import com.splitwise.demo.models.Group;
import com.splitwise.demo.models.User;
import com.splitwise.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class GroupUtils {
    @Autowired
    private static UserService userService;
    public static Group mapToGroup(AddGroupDto groupDto){
        List<Integer> userIds = groupDto.getUsers();
        List<Integer> adminIds = groupDto.getAdmins();
        List<User> users = new ArrayList<>();
        List<User> admins = new ArrayList<>();
        for(int id :userIds){
            User user = userService.getUserById(id);
            users.add(user);
        }

        for(int id :adminIds){
            User user = userService.getUserById(id);
            admins.add(user);
        }

        Group group = new Group();
        group.setName(groupDto.getName());
        group.setDescription(groupDto.getDescription());
        group.setAdmins(admins);
        group.setUsers(users);

        return group;
    }
}
