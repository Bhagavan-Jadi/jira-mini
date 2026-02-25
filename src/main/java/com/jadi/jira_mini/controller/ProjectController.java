package com.jadi.jira_mini.controller;

import com.jadi.jira_mini.dto.request.CreateProjectRequest;
import com.jadi.jira_mini.dto.response.ProjectResponse;
import com.jadi.jira_mini.service.ProjectService;
import com.jadi.jira_mini.util.ApiResponse;
import com.jadi.jira_mini.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<ProjectResponse>> createProject(@Valid @RequestBody CreateProjectRequest request) {

        ProjectResponse response = projectService.CreateProject(request);

        return ResponseUtil.success(response,"Project created successfully");

    }


    @GetMapping
    public Page<ProjectResponse> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String name
    ) {

        return projectService.getAllProjects(page, size, sortBy, direction,active,name);
    }
}
