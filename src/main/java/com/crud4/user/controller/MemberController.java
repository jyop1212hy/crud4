package com.crud4.user.controller;

import com.crud4.common.apiResponse.ApiResponse;
import com.crud4.user.dto.request.CreateMemberRequest;
import com.crud4.user.dto.request.UpdateRequest;
import com.crud4.user.dto.response.*;
import com.crud4.user.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    /**
     * 회원가입
     */
    @PostMapping("/member")
    public ResponseEntity<ApiResponse<CreateMemberResponse>> memberCreate(@RequestBody CreateMemberRequest request) {

        CreateMemberResponse createMember = memberService.create(request);
        ApiResponse<CreateMemberResponse> apiResponse = new ApiResponse<>("created", 200, createMember);
        ResponseEntity<ApiResponse<CreateMemberResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }


    /**
     * 단건 조회
     */
    @GetMapping("/member/{memberId}")
    public ResponseEntity<ApiResponse<FindSingleMemberResponse>> findSingleMember(@PathVariable Long memberId) {
        FindSingleMemberResponse findMember = memberService.findSingle(memberId);
        ApiResponse<FindSingleMemberResponse> apiResponse = new ApiResponse<>("Success", 200, findMember);
        ResponseEntity<ApiResponse<FindSingleMemberResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        return response;
    }


    /**
     * 다건 조회
     */
    @GetMapping("/member")
    public ResponseEntity<ApiResponse<FindAllMemberResponse>> findAllMember() {
        FindAllMemberResponse findAllMemberList = memberService.findAll();
        ApiResponse<FindAllMemberResponse> apiResponse = new ApiResponse<>("Success", 200, findAllMemberList);
        ResponseEntity<ApiResponse<FindAllMemberResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        return response;
    }


    /**
     * 수정
     */
    @PatchMapping("/member/{memberId}")
    public ResponseEntity<ApiResponse<UpdateMemberResponse>> updateResponse(@PathVariable Long memberId, @RequestBody UpdateRequest request) {
        UpdateMemberResponse updateMember = memberService.update(memberId, request);
        ApiResponse<UpdateMemberResponse> apiResponse = new ApiResponse<>("Success", 200, updateMember);
        ResponseEntity<ApiResponse<UpdateMemberResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        return response;
    }


    /**
     * 삭제
     */
    @DeleteMapping("/member/{memberId}")
    public ResponseEntity<ApiResponse<DeleteMemberResponse>> deleteResponse(@PathVariable Long memberId) {
        DeleteMemberResponse updateMember = memberService.delete(memberId);
        ApiResponse<DeleteMemberResponse> apiResponse = new ApiResponse<>("Success", 200, updateMember);
        ResponseEntity<ApiResponse<DeleteMemberResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        return response;
    }

}
