package com.jadi.jira_mini.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateProjectRequest {

    @NotBlank
    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;
}
