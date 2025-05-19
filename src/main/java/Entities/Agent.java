package Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Agent {

    public static Map<String, Agent> agents = new HashMap<>();
    private static int nextId = 1;

    public String agentId;
    public String name;
    public String email;
    public List<String> issueTypes;
    public List<Issue> issues;

    public Agent(String email, String name, List<String> issueTypes) {
        this.agentId = "A" + nextId++;
        this.email = email;
        this.name = name;
        this.issueTypes = issueTypes;
        this.issues = new ArrayList<>();
    }

    public static void addAgent(String email, String name, List<String> issueTypes) {
        Agent agent = new Agent(email, name, issueTypes);
        agents.put(agent.agentId, agent);
        System.out.println("Agent " + agent.agentId + " created");
    }

    public boolean hasActiveIssue() {
        for (Issue issue : issues) {
            if (issue.status == IssueStatus.OPEN) {
                return true;
            }
        }
        return false;
    }

    public static void viewAgentsWorkHistory() {
        for (Agent agent : agents.values()) {
            List<String> issueIds = new ArrayList<>();
            for (Issue issue : agent.issues) {
                issueIds.add(issue.issueId);
            }
            System.out.println(agent.agentId + " -> " + issueIds);
        }
    }
}
