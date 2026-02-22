package com.jadi.jira_mini.repository;

import com.jadi.jira_mini.entity.Issue;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Long> {

    @EntityGraph(attributePaths = {"assignee","reporter","sprint"})
    Optional<Issue> findDetailedById(Long id);
}
