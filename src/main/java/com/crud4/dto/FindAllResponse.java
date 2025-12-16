package com.crud4.dto;

import java.util.List;

public class FindAllResponse {
    private final List<MemberDto> memberList;

    public FindAllResponse(List<MemberDto> memberList) {
        this.memberList = memberList;
    }

    public List<MemberDto> getMemberList() {return memberList;}
}
