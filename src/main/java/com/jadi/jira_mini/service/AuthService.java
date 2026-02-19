package com.jadi.jira_mini.service;

import com.jadi.jira_mini.dto.request.LoginRequest;
import com.jadi.jira_mini.dto.request.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);
    void authenticate(LoginRequest request);






}
