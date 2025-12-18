package com.crud4.comment.dto.requset;

public class CommentRequest {

    private final String title;
    private final String comment;


    public CommentRequest(String title, String comment) {
        this.title = title;
        this.comment = comment;
    }


    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }
}
