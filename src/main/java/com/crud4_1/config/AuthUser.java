package com.crud4_1.config;

public class AuthUser {
    private final Long memberId;

    public AuthUser(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
}
