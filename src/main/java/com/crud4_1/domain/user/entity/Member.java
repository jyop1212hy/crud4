package com.crud4_1.domain.user.entity;

import com.crud4_1.common.entity.BaseTimeEntity;
import jakarta.persistence.*;

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
        if (email != null) {
            this.email = email;
        }
        if (name != null) {
            this.name = name;
        }
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
