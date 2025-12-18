package com.crud4.comment.dto.response;

import java.time.LocalDateTime;

public class FindSingleCommentResponse {

    private final Long id;
    public final String title;
    public final String comment;
    public final LocalDateTime createdAt;
    public final LocalDateTime modifiedAt;

    public FindSingleCommentResponse(Long id, String title, String comment, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.comment = comment;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Long getId() {return id;}
    public String getTitle() {return title;}
    public String getComment() {return comment;}
    public LocalDateTime getCreatedAt() {return createdAt;}
    public LocalDateTime getModifiedAt() {return modifiedAt;}
}
