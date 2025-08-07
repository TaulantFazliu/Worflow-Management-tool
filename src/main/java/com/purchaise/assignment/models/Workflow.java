package com.purchaise.assignment.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Data
public class Workflow {

    private String id;
    private String name;
    private String description;
    @CreatedDate
    private Date createdAt;
    private List<Node> nodes;
    private List<String> readRoles;
    private List<String> writeRoles;

    public Workflow(String id, String name, String description, List<Node> nodes, List<String> readRoles, List<String> writeRoles) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nodes = nodes;
        this.readRoles = readRoles;
        this.writeRoles = writeRoles;
    }
}
