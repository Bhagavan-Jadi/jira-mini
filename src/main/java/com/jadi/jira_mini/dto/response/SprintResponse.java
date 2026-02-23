package com.jadi.jira_mini.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class SprintResponse {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
    private Long projectId;
    private String projectName;
    private LocalDateTime createdAt;

}
