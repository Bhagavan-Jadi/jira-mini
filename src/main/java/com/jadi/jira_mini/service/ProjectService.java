package com.jadi.jira_mini.service;

import com.jadi.jira_mini.dto.request.CreateProjectRequest;
import com.jadi.jira_mini.dto.response.ProjectResponse;
import com.jadi.jira_mini.entity.Project;

import java.util.List;

public interface ProjectService {

    ProjectResponse CreateProject(CreateProjectRequest request);

    List<ProjectResponse> getAllProjects();
}
