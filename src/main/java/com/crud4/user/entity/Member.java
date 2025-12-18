package com.crud4.user.entity;

import com.crud4.common.BaseTimeEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String password;


    //JPA용 기본 생성자
    public Member() {}

    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    //업데이트용
    public void update(String email, String name) {
        if(email != null){this.email = email;}
        if(name != null){this.name = name;}
    }

    public Long getId() {return id;}
    public String getEmail() {return email;}
    public String getName() {return name;}
    public String getPassword() {return password;}
}
