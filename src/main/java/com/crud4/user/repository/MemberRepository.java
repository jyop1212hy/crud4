package com.crud4.user.repository;

import com.crud4.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findAllByDeletedAtIsNull();
}
