package com.jadi.jira_mini.service.impl;

import com.jadi.jira_mini.dto.request.CommentRequest;
import com.jadi.jira_mini.dto.response.CommentResponse;
import com.jadi.jira_mini.entity.Comment;
import com.jadi.jira_mini.entity.Issue;
import com.jadi.jira_mini.entity.User;
import com.jadi.jira_mini.repository.CommentRepository;
import com.jadi.jira_mini.repository.IssueRepository;
import com.jadi.jira_mini.repository.UserRepository;
import com.jadi.jira_mini.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;

    @Override
    public CommentResponse addComment(CommentRequest request) {

        Issue issue = issueRepository.findById(request.getIssueId())
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setIssue(issue);
        comment.setAuthor(author);

        Comment saved = commentRepository.save(comment);

        return mapToResponse(saved);


    }

    private CommentResponse mapToResponse(Comment comment) {

        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getAuthor().getEmail())
                .issueId(comment.getIssue().getId())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CommentResponse> getCommentsByIssue(Long issueId, int page, int size) {

        return commentRepository.findByIssueId(issueId,PageRequest.of(page,size))
                .map(this::mapToResponse);

    }
}
