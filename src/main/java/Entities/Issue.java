package Entities;

import java.util.*;

public class Issue {
    private static int nextId = 1;
    private static Map<String, Issue> issues = new HashMap<>();

    public String issueId;
    public String transactionId;
    public String issueType;
    public String subject;
    public String description;
    public String email;
    public IssueStatus status;
    public String resolution;

    public Issue(String transactionId, String issueType, String subject, String description, String email) {
        this.issueId = "I" + nextId++;
        this.transactionId = transactionId;
        this.issueType = issueType;
        this.subject = subject;
        this.description = description;
        this.email = email;
        this.status = IssueStatus.OPEN;
    }

    public static void createIssue(String transactionId, String issueType, String subject, String description, String email) {
        Issue issue = new Issue(transactionId, issueType, subject, description, email);
        issues.put(issue.issueId, issue);
        System.out.println("Issue " + issue.issueId + " created against transaction " + transactionId);
    }

    public static void assignIssue(String issueId) {
        Issue issue = issues.get(issueId);
        if (issue == null) return;

        for (Agent agent : Agent.agents.values()) {
            if (!agent.hasActiveIssue()) {
                agent.issues.add(issue);
                System.out.println("Issue " + issueId + " assigned to Agent " + agent.agentId);
                return;
            }
        }

        // No free agent, assign randomly
        List<Agent> agentList=new ArrayList<>(Agent.agents.values());
        Agent randomAgent = agentList.get(new Random().nextInt(agentList.size()));
        randomAgent.issues.add(issue);
        System.out.println("Issue " + issueId + " added to waitlist of Agent " + randomAgent.agentId);
    }

    public static void getIssue(String key,String value) {
        for (Issue issue : issues.values()) {
            if (Objects.equals(key,"email") && value.equals(issue.email)) {
                System.out.println(issue.toString());
            } else if (Objects.equals(key, "type") && value.equals(issue.issueType)) {
                System.out.println(issue.toString());
            }
        }
    }

    public static void updateIssue(String issueId, IssueStatus status, String resolution) {
        Issue issue = issues.get(issueId);
        if (issue != null) {
            issue.status = status;
            issue.resolution = resolution;
            System.out.println(issue.issueId + " status updated to " + issue.status);
        }
    }

    public static void resolveIssue(String issueId, String resolution) {
        Issue issue = issues.get(issueId);
        if (issue != null) {
            issue.status = IssueStatus.CLOSED;
            issue.resolution = resolution;
            System.out.println(issue.issueId + " issue marked resolved");
        }
    }

    public  String toString(){
        return issueId+":"+subject+","+issueType+","+email+","+status;
    }

}
