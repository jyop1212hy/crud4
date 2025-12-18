package com.crud4.comment.service;

import org.springframework.data.domain.Page;

import java.util.List;

public class FindAllCommentResponse {

    private final Page<CommentDto> commentList;

    public FindAllCommentResponse(Page<CommentDto> commentList) {
        this.commentList = commentList;
    }

    public Page<CommentDto> getCommentList() {
        return commentList;
    }
}
