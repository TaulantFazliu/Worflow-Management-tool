package com.purchaise.assignment.controllers;

import com.purchaise.assignment.data.DataSeeder;
import com.purchaise.assignment.models.Node;
import com.purchaise.assignment.models.Role;
import com.purchaise.assignment.models.User;
import com.purchaise.assignment.models.Workflow;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workflows")
public class WorkflowController {
    private final DataSeeder dataSeeder;

    @GetMapping
    public List<Workflow> getWorkflows(@RequestParam String username) {
        User user = dataSeeder.users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow();

        List<String> userRoles = user.getRoles().stream().map(Role::getRoleName).toList();

        return dataSeeder.workflows.stream()
                .filter(w -> w.getReadRoles().contains("all") ||
                        w.getReadRoles().stream().anyMatch(userRoles::contains) ||
                        w.getWriteRoles().stream().anyMatch(userRoles::contains))
                .collect(Collectors.toList());
    }

    @GetMapping("/{wfId}/content")
    public List<Object> getContent(@PathVariable String wfId, @RequestParam String username) {
        Workflow workflow = dataSeeder.workflows.stream()
                .filter(w -> w.getId().equals(wfId))
                .findFirst().orElseThrow();

        User user = dataSeeder.users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().orElseThrow();

        List<String> userRoles = user.getRoles().stream().map(Role::getRoleName).toList();

        boolean hasAccess = workflow.getReadRoles().stream().anyMatch(userRoles::contains)
                || workflow.getWriteRoles().stream().anyMatch(userRoles::contains);

        if (!hasAccess) throw new RuntimeException("Unauthorized");

        return dataSeeder.workflowData.get(wfId);
    }

    @PostMapping("/{id}/execute")
    public Workflow execute(@PathVariable String id, @RequestBody MessageTrigger trigger) {
        Workflow workflow = dataSeeder.workflows.stream()
                .filter(w -> w.getId().equals(id))
                .findFirst().orElseThrow();

        for (Node node : workflow.getNodes()) {
            node.setStatus(Node.NodeStatus.IN_PROGRESS);

            switch (node.getType()) {
                case INIT, STORE -> node.setStatus(Node.NodeStatus.COMPLETED);
                case CONDITION -> {
                    if (!"John".equals(trigger.getUsername())) {
                        node.setStatus(Node.NodeStatus.REJECTED);
                        return workflow;
                    }
                    node.setStatus(Node.NodeStatus.COMPLETED);
                }
                case MODIFY -> {
                    trigger.setMessage(trigger.getMessage() + " Hello");
                    node.setStatus(Node.NodeStatus.COMPLETED);
                }
            }
        }
        return workflow;
    }

    @Data
    static class MessageTrigger {
        private String userId;
        private String type;
        private String message;
        private String username;
    }
}
