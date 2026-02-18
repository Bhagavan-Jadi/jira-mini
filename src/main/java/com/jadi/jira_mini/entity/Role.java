package com.jadi.jira_mini.entity;

import com.jadi.jira_mini.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(unique = true,nullable = false)
    private RoleType name;
}
