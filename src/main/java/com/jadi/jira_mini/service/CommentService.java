package com.jadi.jira_mini.service;

import com.jadi.jira_mini.dto.request.CommentRequest;
import com.jadi.jira_mini.dto.response.CommentResponse;
import org.springframework.data.domain.Page;

public interface CommentService {

    CommentResponse addComment(CommentRequest request);

    Page<CommentResponse> getCommentsByIssue(Long issueId,int page,int size);
}
