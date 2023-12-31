package com.splitwise.demo.controllers;

import com.splitwise.demo.dtos.AddGroupDto;
import com.splitwise.demo.dtos.Response;
import com.splitwise.demo.dtos.UserDto;
import com.splitwise.demo.models.Group;
import com.splitwise.demo.models.User;
import com.splitwise.demo.repositories.UserRepository;
import com.splitwise.demo.services.GroupService;
import com.splitwise.demo.services.UserService;
import com.splitwise.demo.utils.GroupUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserController userController;
    @GetMapping("/{groupId}")
    public List<UserDto> getAllUsersByGroupId(@PathVariable int groupId){
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

    @PostMapping("/")
    public Response addGroup(@RequestBody AddGroupDto groupDto){
        Group group = GroupUtils.mapToGroup(groupDto);
        int id = this.groupService.addGroup(group);
        return Response.getSuccessResponse("Group Added Id = " + id);
    }

    @PutMapping("/")
    public Response updateGroup(@RequestBody AddGroupDto groupDto){
        if(Objects.isNull(groupDto.getId())) return Response.getFailureResponse("No Group Found. Invalid or Null Group");
        Group group= GroupUtils.mapToGroup(groupDto);
        group = this.groupService.updateGroup(group);
        return Response.getSuccessResponse("Group Updated Successfully.! group Id = " + group.getId());
    }
}
