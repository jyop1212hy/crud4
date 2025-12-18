package com.crud4.comment.dto.response;


import com.crud4.comment.entity.Comment;

public class CommentResponse {

    private final Long id;
    public final String title;
    public final String comment;

    public CommentResponse(Long id, String title, String comment) {
        this.id = id;
        this.title = title;
        this.comment = comment;
    }

    public static CommentResponse from(Comment savedcomment) {
        return new CommentResponse(savedcomment.getId(),
                savedcomment.getTitle(),
                savedcomment.getComment()
                );
    }

    public Long getId() {return id;}
    public String getTitle() {return title;}
    public String getComment() {return comment;}
}
