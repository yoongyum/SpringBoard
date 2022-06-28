package com.example.boardpractice.repository;

import com.example.boardpractice.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes,Long> {
    Likes save(Likes likes);
    void deleteBySeq(Long seq);
}
