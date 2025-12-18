package com.crud4.comment.repository;


import com.crud4.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByDeletedAtIsNull(Pageable attr0);

    Comment findByDeletedAtIsNull(Long attr0);
}
