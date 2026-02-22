package com.jadi.jira_mini.dto.request;

import com.jadi.jira_mini.enums.IssuePriority;
import com.jadi.jira_mini.enums.IssueType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class IssueRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private IssuePriority priority;

    @NotNull
    private IssueType issueType;

    @NotNull
    private Long sprintId;

    private Long assigneeId;

    private LocalDate dueDate;




}
