package com.splitwise.demo.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class User extends BaseModel{
    private String username;
    private String password;
    private String phoneNo;
}
