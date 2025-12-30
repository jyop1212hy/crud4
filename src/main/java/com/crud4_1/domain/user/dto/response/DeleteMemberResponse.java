package com.crud4_1.domain.user.dto.response;

public class DeleteMemberResponse {

    private final Long id;

    public DeleteMemberResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
