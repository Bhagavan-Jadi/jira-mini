package com.jadi.jira_mini.service.impl;

import com.jadi.jira_mini.dto.request.CreateProjectRequest;
import com.jadi.jira_mini.dto.response.ProjectResponse;
import com.jadi.jira_mini.entity.Project;
import com.jadi.jira_mini.entity.User;
import com.jadi.jira_mini.exception.ResourceNotFoundException;
import com.jadi.jira_mini.repository.ProjectRepository;
import com.jadi.jira_mini.repository.UserRepository;
import com.jadi.jira_mini.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public ProjectResponse CreateProject(CreateProjectRequest request) {

        if(projectRepository.existsByName(request.getName())) {
            throw new ResourceNotFoundException("Project is already Exists");
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User creator = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Not found"));

        Project project = new Project();

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setCreatedBy(creator);

        Project saved = projectRepository.save(project);


        return mapToResponse(saved);
    }



    private ProjectResponse mapToResponse(Project project) {

        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .active(project.isActive())
                .createdAt(project.getCreatedAt())
                .createdBy(project.getCreatedBy().getEmail())
                .build();

    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectResponse> getAllProjects(int page,int size,String sortBy,String direction,Boolean active,String name) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Project> projectPage;

        if(active != null && name != null) {

            projectPage = projectRepository.findByActiveAndNameContainingIgnoreCase(active,name,pageable);
        }else if(active != null) {
            projectPage = projectRepository.findByActive(active,pageable);
        }else {
            projectPage = projectRepository.findByNameContainingIgnoreCase(name,pageable);
        }

        return projectPage.map(this::mapToResponse);

//        return projectRepository.findAll(pageable)
//                .map(this::mapToResponse);


//        return projectRepository.findAll()
//                .stream()
//                .map(this::mapToResponse)
//                .toList();
    }
}
