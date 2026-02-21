package com.jadi.jira_mini.repository;

import com.jadi.jira_mini.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SprintRepository extends JpaRepository<Sprint,Long> {

    boolean existsByName(String name);

    List<Sprint> findByProjectId(Long projectId);
}
