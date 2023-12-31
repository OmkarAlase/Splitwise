package com.splitwise.demo.services;

import com.splitwise.demo.models.Group;
import com.splitwise.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GroupService {
    public List<User> getAllUsersByGroupId(int groupId);
    public int addGroup(Group group);
    public Group getGroup(int id);
    public Group updateGroup(Group group);
}
