package com.jadi.jira_mini.controller;

import com.jadi.jira_mini.dto.request.CreateProjectRequest;
import com.jadi.jira_mini.dto.response.ProjectResponse;
import com.jadi.jira_mini.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','PROJECT_MANAGER')")
    public ProjectResponse createProject(@Valid @RequestBody CreateProjectRequest request) {

        return projectService.CreateProject(request);
    }


    @GetMapping
    public Page<ProjectResponse> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {

        return projectService.getAllProjects(page, size, sortBy, direction);
    }
}
