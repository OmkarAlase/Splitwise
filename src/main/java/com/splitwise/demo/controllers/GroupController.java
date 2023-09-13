package com.splitwise.demo.controllers;

import com.splitwise.demo.dtos.AddGroupDto;
import com.splitwise.demo.dtos.Response;
import com.splitwise.demo.dtos.UserDto;
import com.splitwise.demo.models.Group;
import com.splitwise.demo.models.User;
import com.splitwise.demo.repositories.UserRepository;
import com.splitwise.demo.services.GroupService;
import com.splitwise.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserController userController;
    public List<UserDto> getAllUsersByGroupId(int groupId){
        List<User> users = groupService.getAllUsersByGroupId(groupId);
        List<UserDto> list = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getUsername());
            userDto.setPhoneNo(user.getPhoneNo());
            list.add(userDto);
        }

        return list;
    }

    public Response addGroup(AddGroupDto groupDto){
        List<User> users = new ArrayList<>();
        for(int id : groupDto.getUsers()){
            UserDto userDto = this.userController.findUserById(id);
            User user = new User();
            user.setId(userDto.getId());
            user.setUsername(userDto.getName());
            user.setPhoneNo(userDto.getPhoneNo());
            users.add(user);
        }

        System.out.println(users);
        List<User> admins = new ArrayList<>();
        for(int id : groupDto.getAdmins()){
            UserDto userDto = this.userController.findUserById(id);
            User user = new User();
            user.setId(userDto.getId());
            user.setUsername(userDto.getName());
            user.setPhoneNo(userDto.getPhoneNo());
            admins.add(user);
        }
        System.out.println(admins);
        Group group = new Group();
        group.setName(groupDto.getName());
        group.setDescription(groupDto.getDescription());
        group.setAdmins(admins);
        group.setUsers(users);
        int id = this.groupService.addGroup(group);
        return Response.getSuccessResponse("Group Added Id = " + id);
    }
}
