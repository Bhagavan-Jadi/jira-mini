package com.jadi.jira_mini.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "sprints")
@Getter
@Setter
public class Sprint extends BaseEntity{

    @Column(nullable = false)
    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id",nullable = false)
    private Project project;
}
