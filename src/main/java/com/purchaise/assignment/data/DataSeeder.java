package com.purchaise.assignment.data;

import com.purchaise.assignment.models.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataSeeder {
    public List<User> users = new ArrayList<>();
    public List<Workflow> workflows = new ArrayList<>();
    public Map<String, List<Object>> workflowData = new HashMap<>();


    public DataSeeder() {

        Role adminRole = new Role("1", "Admin");
        Role analystRole = new Role("1", "Analyst");

        users.add(new User("u1", "John", "abc", List.of(adminRole)));
        users.add(new User("u2", "Jane", "abcd", List.of(analystRole)));

        Node init = new Node("node1", Node.NodeType.INIT, Node.NodeStatus.PENDING);
        Node condition = new Node("node2", Node.NodeType.CONDITION, Node.NodeStatus.PENDING);
        Node modify = new Node("node3", Node.NodeType.MODIFY, Node.NodeStatus.PENDING);
        Node store = new Node("node4", Node.NodeType.STORE, Node.NodeStatus.PENDING);

        workflows.add(new Workflow(
                "wf1",
                "Sample workflow",
                "Demo Workflow",
                List.of(init, condition, modify, store),
                List.of("Analyst", "Admin"),
                List.of("Admin")
        ));

        workflowData.put("wf1", List.of(
                new Task("task", "Review", "u2", "u1", "wf1", 1, System.currentTimeMillis(), Task.Priority.HIGH),
                new Note("note", "wf1", "Sample Note"),
                new Attachment("attachment", "wf1", "https://files/dummyfile.pdf")
        ));
    }
}
