import Entities.Agent;
import Entities.Issue;
import Entities.IssueStatus;

import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) {


        Issue.createIssue("T1", "Payment Related", "Payment Failed", "My payment failed", "user1@test.com");
        Issue.createIssue("T2", "Mutual Fund Related", "Purchase Failed", "Could not buy MF", "user2@test.com");
        Issue.createIssue("T3", "Payment Related", "Card Error", "Payment declined", "user2@test.com");

        // Add Agents
        Agent.addAgent("agent1@test.com", "Agent 1", Arrays.asList("Payment Related", "Gold Related"));
        Agent.addAgent("agent2@test.com", "Agent 2", Arrays.asList("Mutual Fund Related"));

        // Assign Issues
        Issue.assignIssue("I1");
        Issue.assignIssue("I2");
        Issue.assignIssue("I3");

        // Filter issues
        Issue.getIssue("email", "user2@test.com");
        Issue.getIssue("type", "Payment Related");

        // Update and Resolve
        Issue.updateIssue("I3", IssueStatus.IN_PROGRESS, "Waiting for payment confirmation");
        Issue.resolveIssue("I3", "Payment Failed Amount will be refunded");

        // View Agent Work History
        Agent.viewAgentsWorkHistory();
    }
}