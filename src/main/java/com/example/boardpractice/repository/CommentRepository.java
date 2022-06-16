package com.example.boardpractice.repository;

import com.example.boardpractice.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Comment save(Comment comment);
    void deleteBySeq(Long seq);
}
