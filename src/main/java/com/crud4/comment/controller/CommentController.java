package com.crud4.comment.controller;

import com.crud4.comment.dto.response.CommentAllResponse;
import com.crud4.comment.dto.requset.CommentRequest;
import com.crud4.comment.dto.response.CommentResponse;
import com.crud4.comment.dto.response.FindSingleCommentResponse;
import com.crud4.comment.dto.requset.UpdateCommentRequest;
import com.crud4.comment.service.CommentService;

import com.crud4.comment.dto.response.UpdateCommentResponse;
import com.crud4.common.apiResponse.ApiResponse;
import com.crud4.user.service.DeleteCommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/comments")
//@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 댓글생성
     */
    @PostMapping("/comment")
    public ResponseEntity<ApiResponse<CommentResponse>> commentCreate(@RequestBody CommentRequest request) {
        CommentResponse commentResponse = commentService.commentCreate(request);
        ApiResponse<CommentResponse> apiResponse = new ApiResponse<>("created", 200, commentResponse);
        ResponseEntity<ApiResponse<CommentResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }

    /**
     * 단건 조회
     */
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse<FindSingleCommentResponse>> findSingleComment(@PathVariable Long commentId) {
        FindSingleCommentResponse findComment = commentService.findSingleComment(commentId);
        ApiResponse<FindSingleCommentResponse> apiResponse = new ApiResponse<>("Success", 200, findComment);
        ResponseEntity<ApiResponse<FindSingleCommentResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }

    /**
     * 다건 조회
     */
    @GetMapping("/comment")
    public ResponseEntity<ApiResponse<Page<CommentAllResponse>>> findAllMember(@RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "10") int size
    ) {
        Page<CommentAllResponse> findAllCommentPage = commentService.findAllComment(page,size);
        ApiResponse<Page<CommentAllResponse>> apiResponse = new ApiResponse<>("Success", 200, findAllCommentPage);
        ResponseEntity<ApiResponse<Page<CommentAllResponse>>> response = ResponseEntity.ok(apiResponse);
        return response;
    }

    /**
     * 댓글 수정
     */
    @PatchMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse<UpdateCommentResponse>> UpdateCommentResponse(@PathVariable Long commentId, @RequestBody UpdateCommentRequest request) {
        UpdateCommentResponse updateCommentResponse = commentService.UpdateCommentResponse(commentId, request);
        ApiResponse<UpdateCommentResponse> apiResponse = new ApiResponse<>("Success", 200, updateCommentResponse);
        ResponseEntity<ApiResponse<UpdateCommentResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse<DeleteCommentResponse>> DeleteResponse(@PathVariable Long commentId) {
        DeleteCommentResponse updateMember = commentService.delete(commentId);
        ApiResponse<DeleteCommentResponse> apiResponse = new ApiResponse<>("Success", 200, updateMember);
        ResponseEntity<ApiResponse<DeleteCommentResponse>> response = ResponseEntity.ok(apiResponse);
        return response;
    }

}
