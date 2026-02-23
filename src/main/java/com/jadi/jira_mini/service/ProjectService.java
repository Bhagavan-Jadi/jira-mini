package com.jadi.jira_mini.service;

import com.jadi.jira_mini.dto.request.CreateProjectRequest;
import com.jadi.jira_mini.dto.response.ProjectResponse;
import com.jadi.jira_mini.entity.Project;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ProjectService {

    ProjectResponse CreateProject(CreateProjectRequest request);

//    List<ProjectResponse> getAllProjects();

    Page<ProjectResponse> getAllProjects(int page,int size,String sortBy,String direction,Boolean active,String name);


}
