package com.example.boardpractice.service;

import com.example.boardpractice.auth.dto.SessionMember;
import com.example.boardpractice.domain.Board;
import com.example.boardpractice.domain.Comment;
import com.example.boardpractice.dto.CommentDto;
import com.example.boardpractice.repository.BoardRepository;
import com.example.boardpractice.repository.CommentRepository;
import com.example.boardpractice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, MemberRepository memberRepository, BoardRepository boardRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    //댓글 추가
    public void addComment(Long seq, SessionMember sessionMember, CommentDto commentDto) {
        Comment comment = Comment.builder(commentDto).build();
        var memberRes = memberRepository.findByEmail(sessionMember.getEmail());

        memberRes.ifPresent(member -> {
            Board board =boardRepository.findBySeq(seq).orElse(null);
            member.addComment(comment,board);
            commentRepository.save(comment);
        });

    }
}
