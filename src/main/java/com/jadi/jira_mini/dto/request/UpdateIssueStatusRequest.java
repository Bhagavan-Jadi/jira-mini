package com.jadi.jira_mini.dto.request;

import com.jadi.jira_mini.enums.IssueStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateIssueStatusRequest {

    @NotNull
    private IssueStatus issueStatus;
}
