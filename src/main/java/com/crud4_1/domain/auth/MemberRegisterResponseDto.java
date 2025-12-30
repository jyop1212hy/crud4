package com.crud4_1.domain.auth;

import com.crud4_1.domain.user.entity.Member;

public class MemberRegisterResponseDto {

    private final Long id;
    private final String name;

    public MemberRegisterResponseDto(Member sevedMember) {
        this.id = sevedMember.getId();
        this.name = sevedMember.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
