package com.crud4.comment.controller;

import com.crud4.comment.dto.CommentRequest;
import com.crud4.comment.dto.CommentResponse;
import com.crud4.comment.dto.FindSingleCommentResponse;
import com.crud4.comment.service.CommentDto;
import com.crud4.comment.service.CommentService;

import com.crud4.comment.service.FindAllCommentResponse;
import com.crud4.comment.service.UpdateCommentResponse;
import com.crud4.common.apiResponse.ApiResponse;
import com.crud4.user.dto.request.UpdateRequest;
import com.crud4.user.dto.response.DeleteMemberResponse;
import com.crud4.user.dto.response.FindAllMemberResponse;
import com.crud4.user.dto.response.UpdateMemberResponse;
import com.crud4.user.service.DeleteCommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController("/comment")
//@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 댓글생성
     */
    @PostMapping("/comments")
    public ResponseEntity<ApiResponse<CommentResponse>> commentCreate(CommentRequest request) {
        CommentResponse commentResponse = commentService.create(request);
        ApiResponse<CommentResponse> apiResponse = new ApiResponse<>("created", 200, commentResponse);
        ResponseEntity<ApiResponse<CommentResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }

    /**
     * 단건 조회
     */
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<FindSingleCommentResponse>> findSingleComment(@PathVariable Long commentId) {
        FindSingleCommentResponse findComment = commentService.findSingleComment(commentId);
        ApiResponse<FindSingleCommentResponse> apiResponse = new ApiResponse<>("Success", 200, findComment);
        ResponseEntity<ApiResponse<FindSingleCommentResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }

    /**
     * 다건 조회
     */
    @GetMapping("/comments")
    public ResponseEntity<ApiResponse<FindAllCommentResponse>> findAllMember(Pageable pageable) {
        Page<CommentDto> findAllCommentList = commentService.findAllComment(pageable);
        FindAllCommentResponse findAllCommentResponse = new FindAllCommentResponse(findAllCommentList);
        ApiResponse<FindAllCommentResponse> apiResponse = new ApiResponse<>("Success", 200, findAllCommentResponse);
        ResponseEntity<ApiResponse<FindAllCommentResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }

    /**
     * 댓글 수정
     */
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<UpdateCommentResponse>> UpdateResponse(@PathVariable Long commentId, @RequestBody UpdateCommentRequest request) {
        UpdateCommentResponse updateCommentMember = commentService.update(commentId, request);
        ApiResponse<UpdateCommentResponse> apiResponse = new ApiResponse<>("Success", 200, updateCommentMember);
        ResponseEntity<ApiResponse<UpdateCommentResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<DeleteCommentResponse>> DeleteResponse(@PathVariable Long memberId) {
        DeleteCommentResponse updateMember = commentService.delete(memberId);
        ApiResponse<DeleteCommentResponse> apiResponse = new ApiResponse<>("Success", 200, updateMember);
        ResponseEntity<ApiResponse<DeleteCommentResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }

}
