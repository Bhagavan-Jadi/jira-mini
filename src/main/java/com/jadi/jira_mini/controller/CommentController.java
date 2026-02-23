package com.jadi.jira_mini.controller;

import com.jadi.jira_mini.dto.request.CommentRequest;
import com.jadi.jira_mini.dto.response.CommentResponse;
import com.jadi.jira_mini.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentResponse addComment(@Valid @RequestBody CommentRequest request) {

        return commentService.addComment(request);
    }

    @GetMapping
    public Page<CommentResponse> getComments(
            @RequestParam Long issueId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return commentService.getCommentsByIssue(issueId, page, size);
    }

}
