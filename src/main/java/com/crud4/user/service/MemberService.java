package com.crud4.user.service;

import com.crud4.user.dto.response.DeleteMemberResponse;
import com.crud4.user.dto.request.UpdateRequest;
import com.crud4.user.dto.response.UpdateMemberResponse;
import com.crud4.user.dto.response.FindAllMemberResponse;
import com.crud4.user.dto.response.FindSingleMemberResponse;
import com.crud4.user.dto.request.CreateMemberRequest;
import com.crud4.user.dto.response.CreateMemberResponse;

import com.crud4.user.dto.MemberDto;
import com.crud4.user.entity.Member;
import com.crud4.user.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    @Transactional
    public CreateMemberResponse create(CreateMemberRequest request) {

        //엔터티 등록
        Member member = new Member(request.getEmail(), request.getName(), request.getPassword());

        //데이터베이스 저장
        Member savedUser = memberRepository.save(member);

        //데이터 추출
        Long savedUserId = savedUser.getId();
        String savedUserEmail = savedUser.getEmail();
        String savedUserName = savedUser.getName();
        LocalDateTime createdAt = savedUser.getCreatedAt();
        LocalDateTime updatedAt = savedUser.getUpdatedAt();

        //DTO 담기
        CreateMemberResponse response = new CreateMemberResponse(savedUserId, savedUserEmail, savedUserName, createdAt, updatedAt);
        return response;
    }

    /**
     * 단건 조회
     */
    @Transactional
    public FindSingleMemberResponse findSingle(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("아이디가 없습니다."));

        //데이터 추출
        Long findId = member.getId();
        String findEmail = member.getEmail();
        String findName = member.getName();
        LocalDateTime createdAt = member.getCreatedAt();

        //DTO 담기
        FindSingleMemberResponse response = new FindSingleMemberResponse(findId, findEmail, findName, createdAt);

        return response;
    }


    /**
     * 다건 조회
     */
    @Transactional
    public FindAllMemberResponse findAll() {

        Member member = memberRepository.findAllByDeletedAtIsNull();

        //데이터 추출
        Long findId = member.getId();
        String findEmail = member.getEmail();
        String findName = member.getName();
        LocalDateTime createdAt = member.getCreatedAt();
        LocalDateTime updatedAt = member.getUpdatedAt();

        //DTO 담기
        MemberDto memberDto = new MemberDto(findId, findEmail, findName, createdAt, updatedAt);
        List<MemberDto> listResponse = new ArrayList<>();
        listResponse.add(memberDto);
        FindAllMemberResponse response = new FindAllMemberResponse(listResponse);
        return response;
    }


    /**
     * 수정
     */
    @Transactional
    public UpdateMemberResponse update(Long memberId, UpdateRequest request) {

        //데이터베이스 ID조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 없습니다."));

        //데이터 추출
        String email = request.getEmail();
        String name = request.getName();

        //엔터티 등록
        member.update(email, name);

        //데이터 추출
        Long savedUserId = member.getId();
        String savedUserEmail = member.getEmail();
        String savedUserName = member.getName();
        LocalDateTime createdAt = member.getCreatedAt();
        LocalDateTime updatedAt = member.getUpdatedAt();

        //DTO 담기
        UpdateMemberResponse response = new UpdateMemberResponse(savedUserId, savedUserEmail, savedUserName, createdAt, updatedAt);
        return response;
    }


    /**
     * 삭제
     */
    @Transactional
    public DeleteMemberResponse delete(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("아이디가 없습니다."));

        //데이터베이스 저장
        memberRepository.delete(member);

        //데이터 추출
        Long savedUserId = member.getId();

        //DTO 담기
        DeleteMemberResponse response = new DeleteMemberResponse(savedUserId);
        return response;
    }

}
