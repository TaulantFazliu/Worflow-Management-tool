package com.purchaise.assignment.models;

import lombok.Data;

@Data
public class Attachment {
    private String type;
    public String workflowId;
    public String url;

    public Attachment(String type, String workflowId, String url) {
        this.type = type;
        this.workflowId = workflowId;
        this.url = url;
    }
}
