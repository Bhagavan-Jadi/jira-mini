package com.jadi.jira_mini.controller;


import com.jadi.jira_mini.dto.request.IssueRequest;
import com.jadi.jira_mini.dto.response.IssueResponse;
import com.jadi.jira_mini.service.IssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;


    @PostMapping
    public IssueResponse createIssue(@Valid @RequestBody IssueRequest request) {

        return issueService.createIssue(request);
    }

    @GetMapping
    public Page<IssueResponse> geIssues(
            @RequestParam Long sprintId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return issueService.getIssuesBySprint(sprintId, page, size);
    }
}
