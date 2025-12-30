package com.crud4_1.domain.user.repository;

import com.crud4_1.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findAllByDeletedAtIsNull();

    Member findByDeletedAtIsNull(Long memberId);

    Boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);
}
