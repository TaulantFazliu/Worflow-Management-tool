package com.purchaise.assignment.models;

import lombok.Data;

@Data
public class Node {

    public enum NodeType {
        INIT, CONDITION, MODIFY, STORE
    }

    public enum NodeStatus {
        PENDING, IN_PROGRESS, COMPLETED, REJECTED
    }

    private String id;
    private NodeType type;
    private NodeStatus status;

    public Node(String id, NodeType type, NodeStatus status) {
        this.id = id;
        this.type = type;
        this.status = status;
    }
}
