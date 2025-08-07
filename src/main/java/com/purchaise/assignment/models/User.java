package com.purchaise.assignment.models;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private List<Role> roles;

    public User(String id, String username, String password, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
