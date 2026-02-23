package com.jadi.jira_mini.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String AdminAccess() {
        return "Welcome Admin";
    }

    @GetMapping("/developer")
    public String developerAccess() {
        return "Check console";
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('TESTER')")
    public String TesterAccess() {
        return "Welcome Tester";
    }

    @GetMapping("/pm")
    @PreAuthorize("hasRole('PROJECT_MANAGER')")
    public String PmAccess() {
        return "Welcome Project Manager";
    }

    @GetMapping("/common")
    public String commonAccess() {
        return "Any authenticated user can access this.";
    }
}
