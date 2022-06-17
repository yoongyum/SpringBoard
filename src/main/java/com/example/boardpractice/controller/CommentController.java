package com.example.boardpractice.controller;

import com.example.boardpractice.auth.dto.SessionMember;
import com.example.boardpractice.dto.BoardDto;
import com.example.boardpractice.dto.CommentDto;
import com.example.boardpractice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;

    //댓글 달기
    @PostMapping("/comment{seq}")
    public String createComment(Long seq, CommentDto commentDto, HttpSession session){
        System.out.println("게시판 번호 : "+seq);
        System.out.println("댓글 : "+ commentDto.getContent());
        SessionMember sessionMember = (SessionMember) session.getAttribute("member");
        System.out.println("세션 멤버 : "+ sessionMember.getName());
        commentService.addComment(seq,sessionMember,commentDto);

        return "redirect:/board/view?seq="+seq;
    }

}
