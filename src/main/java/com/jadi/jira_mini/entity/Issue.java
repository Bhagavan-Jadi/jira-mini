package com.jadi.jira_mini.entity;

import com.jadi.jira_mini.enums.IssuePriority;
import com.jadi.jira_mini.enums.IssueStatus;
import com.jadi.jira_mini.enums.IssueType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "issues")
@Getter
@Setter
public class Issue extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus = IssueStatus.TODO;

    @Enumerated(EnumType.STRING)
    private IssueType issueType;

    @Enumerated(EnumType.STRING)
    private IssuePriority issuePriority;

    private LocalDate dueDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id",nullable = false)
    private Sprint sprint;

}
