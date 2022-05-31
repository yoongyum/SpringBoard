package com.example.boardpractice.repository;

import com.example.boardpractice.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    Board save(Board board);
    Optional<Board> findBySeq(Long seq);
    void deleteBySeq(Long seq);
}
