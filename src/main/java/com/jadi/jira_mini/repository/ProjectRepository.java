package com.jadi.jira_mini.repository;

import com.jadi.jira_mini.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    Optional<Project> findByName(String name);

    boolean existsByName(String name);

    Page<Project> findByActive(boolean active, Pageable pageable);

    Page<Project> findByNameContainingIgnoreCase(String name,Pageable pageable);

    Page<Project> findByActiveAndNameContainingIgnoreCase(boolean active,String name,Pageable pageable);
}
