package com.crud4.user.dto.response;

import java.time.LocalDateTime;

public class FindSingleMemberResponse {

    private final Long id;
    private final String email;
    private final String name;
    private final LocalDateTime createdAt;

    public FindSingleMemberResponse(Long id, String email, String name, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
    }

    public Long getId() {return id;}
    public String getEmail() {return email;}
    public String getName() {return name;}
    public LocalDateTime getCreatedAt() {return createdAt;}
}
