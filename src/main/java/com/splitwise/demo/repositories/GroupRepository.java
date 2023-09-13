package com.splitwise.demo.repositories;

import com.splitwise.demo.models.Group;
import com.splitwise.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    List<User> findUsersById(int groupId);
}
