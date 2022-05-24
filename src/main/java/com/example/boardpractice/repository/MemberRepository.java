package com.example.boardpractice.repository;

import com.example.boardpractice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member save(Member member);
    Optional<Member> findBySeq(Long seq);
    Optional<Member> findById(String id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
