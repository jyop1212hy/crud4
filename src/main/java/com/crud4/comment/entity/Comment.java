package com.crud4.comment.entity;

import com.crud4.common.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title", length = 60, nullable = false)
    public String title;

    @Column(name = "comment", length = 60, nullable = false)
    public String comment;

    public Comment(String title, String comment) {
        this.title = title;
        this.comment = comment;
    }

    //JPA용 기본 생성자
    public Comment(){}

    //업데이트용
    public void update(String title, String comment) {
        this.title = title;
        this.comment = comment;
    }

    public Long getId() {return id;}
    public String getTitle() {return title;}
    public String getComment() {return comment;}
}
