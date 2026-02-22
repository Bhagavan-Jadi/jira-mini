package com.jadi.jira_mini.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponse {

    private Long id;
    private String content;
    private String author;
    private Long issueId;
    private LocalDateTime createdAt;
}
