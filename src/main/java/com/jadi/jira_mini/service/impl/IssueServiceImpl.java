package com.jadi.jira_mini.service.impl;

import com.jadi.jira_mini.dto.request.IssueRequest;
import com.jadi.jira_mini.dto.response.IssueResponse;
import com.jadi.jira_mini.entity.Issue;
import com.jadi.jira_mini.entity.Sprint;
import com.jadi.jira_mini.entity.User;
import com.jadi.jira_mini.enums.IssueStatus;
import com.jadi.jira_mini.exception.ResourceNotFoundException;
import com.jadi.jira_mini.repository.IssueRepository;
import com.jadi.jira_mini.repository.SprintRepository;
import com.jadi.jira_mini.repository.UserRepository;
import com.jadi.jira_mini.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final SprintRepository sprintRepository;
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;

    @Override
    public IssueResponse createIssue(IssueRequest request) {

        Sprint sprint = sprintRepository.findById(request.getSprintId())
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found"));

         String email = SecurityContextHolder.getContext().getAuthentication().getName();

         User reporter = userRepository.findByEmail(email)
                 .orElseThrow(() -> new ResourceNotFoundException("Reporter not found"));

         Issue issue = new Issue();

         issue.setTitle(request.getTitle());
         issue.setDescription(request.getDescription());
         issue.setIssuePriority(request.getPriority());
         issue.setIssueType(request.getIssueType());
         issue.setDueDate(request.getDueDate());
         issue.setIssueStatus(IssueStatus.TODO);
         issue.setSprint(sprint);
         issue.setReporter(reporter);

         if(request.getAssigneeId() != null) {
             User assignee = userRepository.findById(request.getAssigneeId())
                     .orElseThrow(() -> new ResourceNotFoundException("Assignee not found"));

             issue.setAssignee(assignee);
         }

         Issue saved = issueRepository.save(issue);

         return mapToResponse(saved);


    }

    private IssueResponse mapToResponse(Issue issue) {

        return IssueResponse.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .description(issue.getDescription())
                .status(issue.getIssueStatus())
                .priority(issue.getIssuePriority())
                .issueType(issue.getIssueType())
                .dueDate(issue.getDueDate())
                .assignee(issue.getAssignee() != null ?
                        issue.getAssignee().getEmail() : null)
                .reporter(issue.getReporter().getEmail())
                .sprintId(issue.getSprint().getId())
                .createdAt(issue.getCreatedAt())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IssueResponse> getIssuesBySprint(
            Long sprintId,
            int page,
            int size) {

        return issueRepository.findAll(PageRequest.of(page,size))
                .map(this::mapToResponse);
    }
}
