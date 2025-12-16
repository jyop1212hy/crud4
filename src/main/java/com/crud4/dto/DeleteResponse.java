package com.crud4.dto;

public class DeleteResponse {

    private final Long id;

    public DeleteResponse(Long id) {
        this.id = id;
    }

    public Long getId() {return id;}
}
