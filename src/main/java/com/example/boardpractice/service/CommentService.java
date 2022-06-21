package com.example.boardpractice.service;

import com.example.boardpractice.auth.dto.SessionMember;
import com.example.boardpractice.domain.Board;
import com.example.boardpractice.domain.Comment;
import com.example.boardpractice.dto.CommentDto;
import com.example.boardpractice.repository.BoardRepository;
import com.example.boardpractice.repository.CommentRepository;
import com.example.boardpractice.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Slf4j
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

    //댓글기능 유효성 체크
    public Map<String, String> validationHandler(Errors errors) {
        Map<String, String> validator = new HashMap<>();

        for(FieldError error: errors.getFieldErrors()){
            //key = valid_{Dto 필드명}
            String key = String.format("valid_%s", error.getField());
            validator.put(key,error.getDefaultMessage());
        }
        return validator;
    }

    public long removeComment(Long seq) {
        Comment comment = commentRepository.findById(seq).orElse(null);
        log.info("지울 댓글 : {}",comment.getContent());
        long boardSeq = comment.getBoard().getSeq();
        commentRepository.deleteBySeq(seq);
        return boardSeq;
    }
}
