package com.crud4.user.service;

import com.crud4.comment.entity.Comment;

public class DeleteCommentResponse {

    private final Long id;

    public DeleteCommentResponse(Long id) {
        this.id = id;
    }

    public static DeleteCommentResponse from(Comment comment) {
        return new DeleteCommentResponse(comment.getId());
    }

    public Long getId() {
        return id;
    }
}
