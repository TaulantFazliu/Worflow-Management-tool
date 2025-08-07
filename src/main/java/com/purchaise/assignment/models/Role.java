package com.purchaise.assignment.models;

import lombok.Data;

@Data
public class Role {

    private String id;
    private String roleName;

    public Role(String id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
}
