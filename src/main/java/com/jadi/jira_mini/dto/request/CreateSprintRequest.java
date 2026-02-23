package com.jadi.jira_mini.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateSprintRequest {

    @NotBlank
    private String name;

    @NotNull
    private Long projectId;

    private LocalDate startDate;

    private LocalDate endDate;
}
