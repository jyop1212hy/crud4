package com.crud4.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 60, nullable = false)
    private String email;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    //JPA용 기본 생성자
    public Member() {}

    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    //업데이트용
    public void update(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Long getId() {return id;}
    public String getEmail() {return email;}
    public String getName() {return name;}
    public String getPassword() {return password;}
    public LocalDateTime getCreatedAt() {return createdAt;}
    public LocalDateTime getUpdatedAt() {return updatedAt;}
    public boolean isDeleted() {return isDeleted;}

}
