package com.purchaise.assignment.models;

import lombok.Data;

@Data
public class Note {
    private String type;
    private String workflowId;
    private String text;

    public Note(String type, String workflowId, String text) {
        this.type = type;
        this.workflowId = workflowId;
        this.text = text;
    }
}
