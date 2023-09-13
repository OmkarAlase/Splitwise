package com.splitwise.demo.commands;

import com.splitwise.demo.controllers.GroupController;
import com.splitwise.demo.dtos.AddGroupDto;
import com.splitwise.demo.dtos.Response;
import com.splitwise.demo.dtos.UserDto;
import com.splitwise.demo.exceptions.InvalidCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddGroupCommand extends Command{
    private static final String ADD_GROUP_COMMAND = "Add_Group";
    private GroupController groupController;
    @Autowired
    public AddGroupCommand(GroupController groupController){
        CommandRegistry.getInstance().register(ADD_GROUP_COMMAND,this);
        this.groupController = groupController;
    }
    @Override
    public void validate(String command) throws InvalidCommandException {
        System.out.println("Inside Valid");
        if(StringUtils.isEmpty(command)){
            throw new InvalidCommandException("Invalid Command");
        }
        String[] splits = command.split(" ");;
        if(splits.length < 5 || !splits[0].equals("Add_Group")) {
            throw new InvalidCommandException("Invalid Command.");
        }
    }

    @Override
    public void execute(String command) {
        String[] splits = command.split(" ");
        String groupName = splits[1];
        String groupDescription = splits[2];
        int userId = Integer.parseInt(splits[3]);
        int adminId = Integer.parseInt(splits[4]);

        List<Integer> users = new ArrayList<>();
        users.add(userId);
        List<Integer> admins = new ArrayList<>();
        admins.add(adminId);

        AddGroupDto dto = new AddGroupDto();
        dto.setAdmins(admins);
        dto.setName(groupName);
        dto.setDescription(groupDescription);
        dto.setUsers(users);

        Response response = groupController.addGroup(dto);

        System.out.println(response);
    }
}
