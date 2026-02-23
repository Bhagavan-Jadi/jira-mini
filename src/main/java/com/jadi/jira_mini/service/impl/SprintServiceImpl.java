package com.jadi.jira_mini.service.impl;

import com.jadi.jira_mini.dto.request.CreateSprintRequest;
import com.jadi.jira_mini.dto.response.SprintResponse;
import com.jadi.jira_mini.entity.Project;
import com.jadi.jira_mini.entity.Sprint;
import com.jadi.jira_mini.exception.ResourceNotFoundException;
import com.jadi.jira_mini.repository.ProjectRepository;
import com.jadi.jira_mini.repository.SprintRepository;
import com.jadi.jira_mini.service.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {

    private final SprintRepository sprintRepository;
    private final ProjectRepository projectRepository;

    @Override
    public SprintResponse createSprint(CreateSprintRequest request) {

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        Sprint sprint = new Sprint();
        sprint.setName(request.getName());
        sprint.setStartDate(request.getStartDate());
        sprint.setEndDate(request.getEndDate());
        sprint.setProject(project);

        Sprint saved = sprintRepository.save(sprint);

        return mapToResponse(saved);


    }

    private SprintResponse mapToResponse(Sprint sprint) {

        return SprintResponse.builder()
                .id(sprint.getId())
                .name(sprint.getName())
                .startDate(sprint.getStartDate())
                .endDate(sprint.getEndDate())
                .active(sprint.isActive())
                .projectId(sprint.getProject().getId())
                .projectName(sprint.getProject().getName())
                .createdAt(sprint.getCreatedAt())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SprintResponse> getSprintsByProject(Long projectId, int page, int size) {

        Pageable pageable = PageRequest.of(page,size);

        Page<Sprint> sprints = sprintRepository.findByProjectId(projectId,pageable);

        Page<SprintResponse> responses = sprints.map(this::mapToResponse);

        responses.getContent().forEach(sprintResponse -> {
            System.out.println("Sprint dto ; ->"+ sprintResponse);
        });

        return responses;




//        return sprintRepository.findAll(PageRequest.of(page,size))
//                .map(this::mapToResponse);




    }
}
