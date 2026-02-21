package com.jadi.jira_mini.controller;

import com.jadi.jira_mini.dto.request.CreateSprintRequest;
import com.jadi.jira_mini.dto.response.SprintResponse;
import com.jadi.jira_mini.service.SprintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sprints")
@RequiredArgsConstructor
public class SprintController {

    private final SprintService sprintService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','PROJECT_MANAGER')")
    public SprintResponse createSprint(@Valid @RequestBody CreateSprintRequest request) {

        return sprintService.createSprint(request);

    }

    @GetMapping
    public Page<SprintResponse> getSprints(
            @RequestParam Long projectId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size

    ) {

        System.out.println("Project id " + projectId);

        return sprintService.getSprintsByProject(projectId, page, size);

    }
}
