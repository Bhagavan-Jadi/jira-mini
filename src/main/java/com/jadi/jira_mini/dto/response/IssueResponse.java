package com.jadi.jira_mini.dto.response;

import com.jadi.jira_mini.enums.IssuePriority;
import com.jadi.jira_mini.enums.IssueStatus;
import com.jadi.jira_mini.enums.IssueType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class IssueResponse {

    private Long id;
    private String title;
    private String description;
    private IssueStatus status;
    private IssuePriority priority;
    private IssueType issueType;
    private LocalDate dueDate;
    private String assignee;
    private String reporter;
    private Long sprintId;
    private LocalDateTime createdAt;
}
