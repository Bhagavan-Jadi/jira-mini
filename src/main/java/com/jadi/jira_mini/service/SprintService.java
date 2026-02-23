package com.jadi.jira_mini.service;

import com.jadi.jira_mini.dto.request.CreateSprintRequest;
import com.jadi.jira_mini.dto.response.SprintResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface SprintService {

    SprintResponse createSprint(CreateSprintRequest request);

    Page<SprintResponse> getSprintsByProject(Long projectId,int page,int size);
}
