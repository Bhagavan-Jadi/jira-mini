package com.jadi.jira_mini.service.impl;

import com.jadi.jira_mini.dto.request.LoginRequest;
import com.jadi.jira_mini.dto.request.RegisterRequest;
import com.jadi.jira_mini.entity.Role;
import com.jadi.jira_mini.entity.User;
import com.jadi.jira_mini.enums.RoleType;
import com.jadi.jira_mini.repository.RoleRepository;
import com.jadi.jira_mini.repository.UserRepository;
import com.jadi.jira_mini.security.JwtTokenProvider;
import com.jadi.jira_mini.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private  final JwtTokenProvider jwtTokenProvider;

    @Override
    public void register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Role defaultRole = roleRepository.findByName(RoleType.DEVELOPER)
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(defaultRole));

        userRepository.save(user);
    }

    @Override
    public String authenticate(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        return jwtTokenProvider.generateToken(request.getEmail());

    }
}
