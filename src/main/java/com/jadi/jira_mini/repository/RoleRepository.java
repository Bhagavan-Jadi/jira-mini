package com.jadi.jira_mini.repository;

import com.jadi.jira_mini.entity.Role;
import com.jadi.jira_mini.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(RoleType name);
}
