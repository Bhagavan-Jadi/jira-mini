package com.jadi.jira_mini.config;

import com.jadi.jira_mini.entity.Role;
import com.jadi.jira_mini.enums.RoleType;
import com.jadi.jira_mini.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        for (RoleType roleType : RoleType.values()) {

            roleRepository.findByName(roleType)
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName(roleType);
                        return roleRepository.save(role);
                    });
        }
    }
}
