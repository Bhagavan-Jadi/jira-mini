package com.jadi.jira_mini.service;

import com.jadi.jira_mini.dto.request.IssueRequest;
import com.jadi.jira_mini.dto.response.IssueResponse;
import com.jadi.jira_mini.enums.IssueStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IssueService {

    IssueResponse createIssue(IssueRequest request);

    Page<IssueResponse> getIssuesBySprint(Long sprintId, int page,int size);

    IssueResponse updateStatus(Long issueId, IssueStatus newStatus);
}
