package com.splitwise.demo.repositories;

import com.splitwise.demo.models.Group;
import com.splitwise.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    List<User> findUsersById(int groupId);
    @Query("SELECT g FROM Group g JOIN g.users u WHERE u.id = :userId")
    List<Group> getGroupsByUserId(int userId);
    @Query("SELECT g FROM Group g JOIN g.admins a WHERE a.id = :userId")
    List<Group> getGroupsByAdminUserId(int userId);
}
