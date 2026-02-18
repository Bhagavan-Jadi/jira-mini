package com.jadi.jira_mini.repository;

import com.jadi.jira_mini.entity.Role;
import com.jadi.jira_mini.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(RoleType name);
}
