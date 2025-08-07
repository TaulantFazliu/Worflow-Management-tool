package com.purchaise.assignment.models;

import lombok.Data;

@Data
public class Task {
    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    private String type;
    private String title;
    private String assigneeId;
    private String reporterId;
    private String workflowId;
    private int order;
    private long time;
    private Priority priority;

    public Task(String type, String title, String assigneeId, String reporterId, String workflowId, int order, long time, Priority priority) {
        this.type = type;
        this.title = title;
        this.assigneeId = assigneeId;
        this.reporterId = reporterId;
        this.workflowId = workflowId;
        this.order = order;
        this.time = time;
        this.priority = priority;
    }
}
