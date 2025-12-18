package com.crud4.comment.service;

import com.crud4.comment.controller.UpdateCommentRequest;
import com.crud4.comment.dto.FindSingleCommentResponse;
import com.crud4.comment.entity.Comment;
import com.crud4.comment.repository.CommentRepository;
import com.crud4.comment.dto.CommentRequest;
import com.crud4.comment.dto.CommentResponse;

import com.crud4.user.service.DeleteCommentResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    /**
     * 댓글생성
     */
    @Transactional
    public CommentResponse create(CommentRequest request) {
        //게시글 PK가 존재하는지 데이터 베이스 검증만하기

        //request 입력값 추출
        String requestTitle = request.getTitle();
        String requestComment = request.getComment();

        //엔터티 등록
        Comment comment = new Comment(requestTitle, requestComment);

        //데이터베이스에 저장후 데이터 거내기
        Comment savedComment = commentRepository.save(comment);

        //DTO 전달
        return CommentResponse.from(savedComment);
    }


    /**
     * 단건 조회
     */
    @Transactional
    public FindSingleCommentResponse findSingleComment(Long commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("아이디가 없습니다."));

        //데이터 추출
        Long findId = comment.getId();
        String findEmail = comment.getTitle();
        String findName = comment.getComment();
        LocalDateTime createdAt = comment.getCreatedAt();
        LocalDateTime modifiedAt = comment.getModifiedAt();

        //DTO 담기
        FindSingleCommentResponse response = new FindSingleCommentResponse(findId, findEmail, findName, createdAt, modifiedAt);

        return response;
    }


    /**
     * 다건 조회
     */
    @Transactional
    public Page<CommentDto> findAllComment(Pageable pageable) {

        Page<Comment> commentsPage = commentRepository.findAllByDeletedAtIsNull(pageable);
        return commentsPage.map(comment -> new CommentDto(
                comment.getId(),
                comment.getTitle(),
                comment.getComment(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        ));
    }


    /**
     * 댓글 수저
     */
    @Transactional
    public UpdateCommentResponse update(Long commentId, UpdateCommentRequest request) {

        //아이디 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("수정할 댓글이 없습니다."));

        //수정할 데이터를 엔터티에 등록
        comment.update(comment.getTitle(), comment.getComment());

        //DTO에 담기
        return UpdateCommentResponse.from(comment);


    }

    /**
     * 댓글 삭제
     */
    @Transactional
    public DeleteCommentResponse delete(Long memberId) {

        //데이터 베이스 동일성 검증
//       if(commentRepository.existsById(memberId)) throw new IllegalArgumentException("삭제할 댓글이 없습니다.");

        Comment comment = commentRepository.findByDeletedAtIsNull(memberId);
//                .(()->new IllegalArgumentException("삭제할 댓글이 없습니다."));

        comment.softDelete();
        return DeleteCommentResponse.from(comment);
    }
}
