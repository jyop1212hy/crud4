package com.crud4.comment.dto.response;

import com.crud4.comment.entity.Comment;

import java.time.LocalDateTime;

public class UpdateCommentResponse {

    private final Long id;
    private final String title;
    private final String comment;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdateCommentResponse(Long id, String title, String comment, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.comment = comment;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static UpdateCommentResponse from(Comment comment) {
        return new UpdateCommentResponse(
                comment.getId(),
                comment.getTitle(),
                comment.getComment(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
                );
    }

    public Long getId() {return id;}
    public String getTitle() {return title;}
    public String getComment() {return comment;}
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
