package com.crud4.controller;

import com.crud4.dto.*;
import com.crud4.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    /**
     * 회원가입
     */
    @PostMapping("/members")
    public ResponseEntity<ApiResponse<CreateResponse>> memberCreate(CreateRequest request) {

        CreateResponse createMember = memberService.create(request);
        ApiResponse<CreateResponse> apiResponse = new ApiResponse<>("created", 200, createMember);
        ResponseEntity<ApiResponse<CreateResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }


    /**
     * 단건 조회
     */
    @GetMapping("/members/{memberId}")
    public ResponseEntity<ApiResponse<FindSingleResponse>> findSingleMember(@PathVariable Long memberId) {
        FindSingleResponse findMember = memberService.findSingle(memberId);
        ApiResponse<FindSingleResponse> apiResponse = new ApiResponse<>("Success", 200, findMember);
        ResponseEntity<ApiResponse<FindSingleResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }


    /**
     * 다건 조회
     */
    @GetMapping("/members")
    public ResponseEntity<ApiResponse<FindAllResponse>> findAllMember() {
        FindAllResponse findAllMemberList = memberService.findAll();
        ApiResponse<FindAllResponse> apiResponse = new ApiResponse<>("Success", 200, findAllMemberList);
        ResponseEntity<ApiResponse<FindAllResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }


    /**
     * 수정
     */
    @PatchMapping("/members/{memberId}")
    public ResponseEntity<ApiResponse<UpdateResponse>> UpdateResponse(@PathVariable Long memberId, @RequestBody UpdateRequest request) {
        UpdateResponse updateMember = memberService.update(memberId, request);
        ApiResponse<UpdateResponse> apiResponse = new ApiResponse<>("Success", 200, updateMember);
        ResponseEntity<ApiResponse<UpdateResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }


    /**
     * 삭제
     */
    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<ApiResponse<DeleteResponse>> DeleteResponse(@PathVariable Long memberId) {
        DeleteResponse updateMember = memberService.delete(memberId);
        ApiResponse<DeleteResponse> apiResponse = new ApiResponse<>("Success", 200, updateMember);
        ResponseEntity<ApiResponse<DeleteResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }

}
