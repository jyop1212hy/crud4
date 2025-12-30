package com.crud4_1.domain.user.dto.response;

import com.crud4_1.domain.user.dto.MemberDto;

import java.util.List;

public class FindAllMemberResponse {
    private final List<MemberDto> memberList;

    public FindAllMemberResponse(List<MemberDto> memberList) {
        this.memberList = memberList;
    }

    public List<MemberDto> getMemberList() {
        return memberList;
    }
}
