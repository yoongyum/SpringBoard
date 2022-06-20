package com.example.boardpractice.controller;

import com.example.boardpractice.auth.dto.SessionMember;
import com.example.boardpractice.dto.BoardDto;
import com.example.boardpractice.dto.CommentDto;
import com.example.boardpractice.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;

    //댓글 달기
    @PostMapping("/comment{seq}")
    public String createComment(Long seq, @Valid CommentDto commentDto,Errors errors, RedirectAttributes redirectAttributes, HttpSession session, Model model){
        SessionMember sessionMember = (SessionMember) session.getAttribute("member");

//        if (errors.hasErrors()){
//
//            Map<String, String> validationResult = commentService.validationHandler(errors);
//
//            for (String key : validationResult.keySet()) {//key : 메세지 -> 파라미터로 전달
//                log.info("error = {}",key);
//                log.info("test = {}",validationResult.get(key));
//                redirectAttributes.addAttribute(key, validationResult.get(key));
//            }
//            return "redirect:/board/view?seq="+seq;
//        }
        commentService.addComment(seq,sessionMember,commentDto);

        return "redirect:/board/view?seq="+seq;
    }

}
