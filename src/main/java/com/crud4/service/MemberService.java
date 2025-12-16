package com.crud4.service;

import com.crud4.dto.DeleteResponse;
import com.crud4.dto.UpdateRequest;
import com.crud4.dto.UpdateResponse;
import com.crud4.dto.FindAllResponse;
import com.crud4.dto.FindSingleResponse;
import com.crud4.dto.CreateRequest;
import com.crud4.dto.CreateResponse;

import com.crud4.dto.MemberDto;
import com.crud4.entity.Member;
import com.crud4.repository.MemberRepository;
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
    public CreateResponse create(CreateRequest request) {

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
        CreateResponse response = new CreateResponse(savedUserId, savedUserEmail, savedUserName, createdAt, updatedAt);
        return response;
    }

    /**
     * 단건 조회
     */
    @Transactional
    public FindSingleResponse findSingle(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("아이디가 없습니다."));

        //데이터 추출
        Long findId = member.getId();
        String findEmail = member.getEmail();
        String findName = member.getName();
        LocalDateTime createdAt = member.getCreatedAt();

        //DTO 담기
        FindSingleResponse response = new FindSingleResponse(findId, findEmail, findName, createdAt);

        return response;
    }


    /**
     * 다건 조회
     */
    @Transactional
    public FindAllResponse findAll() {
        Member member = memberRepository.findByDeletedFalse();

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
        FindAllResponse response = new FindAllResponse(listResponse);
        return response;
    }


    /**
     * 수정
     */
    @Transactional
    public UpdateResponse update(Long memberId, UpdateRequest request) {

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
        UpdateResponse response = new UpdateResponse(savedUserId, savedUserEmail, savedUserName, createdAt, updatedAt);
        return response;
    }


    /**
     * 삭제
     */
    @Transactional
    public DeleteResponse delete(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("아이디가 없습니다."));

        //데이터베이스 저장
        memberRepository.delete(member);

        //데이터 추출
        Long savedUserId = member.getId();

        //DTO 담기
        DeleteResponse response = new DeleteResponse(savedUserId);
        return response;
    }

}
