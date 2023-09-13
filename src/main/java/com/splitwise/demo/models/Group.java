package com.splitwise.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "groups")
public class Group extends BaseModel{
    private String name;
    private String description;
    @ManyToMany
    private List<User> users;
    @ManyToMany
    private List<User> admins;
}
