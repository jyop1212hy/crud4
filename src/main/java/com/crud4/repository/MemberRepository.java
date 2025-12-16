package com.crud4.repository;

import com.crud4.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByDeletedFalse();
}
