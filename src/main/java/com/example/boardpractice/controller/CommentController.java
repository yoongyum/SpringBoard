package com.example.boardpractice.controller;

import com.example.boardpractice.auth.dto.SessionMember;
import com.example.boardpractice.dto.BoardDto;
import com.example.boardpractice.dto.CommentDto;
import com.example.boardpractice.service.BoardService;
import com.example.boardpractice.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;

//    //댓글 달기
//    @PostMapping("/comment{seq}")
//    public String createComment(Long seq, @Valid CommentDto commentDto,Errors errors, RedirectAttributes redirectAttributes, HttpSession session, Model model){
//        SessionMember sessionMember = (SessionMember) session.getAttribute("member");
//
////        if (errors.hasErrors()){
////
////            Map<String, String> validationResult = commentService.validationHandler(errors);
////
////            for (String key : validationResult.keySet()) {//key : 메세지 -> 파라미터로 전달
////                log.info("error = {}",key);
////                log.info("test = {}",validationResult.get(key));
////                redirectAttributes.addAttribute(key, validationResult.get(key));
////            }
////            return "redirect:/board/view?seq="+seq;
////        }
//        commentService.addComment(sessionMember,commentDto);
//
//        return "redirect:/board/view?seq="+seq;
//    }

    //댓글 생성
    @PostMapping("/comment/insert")
    public String addComment(CommentDto commentDto, HttpSession session, Model model){
        SessionMember sessionMember = (SessionMember) session.getAttribute("member");
        commentService.addComment(sessionMember,commentDto);
        model.addAttribute("selectedBoard",boardService.getBoard(commentDto.getBoardSeq()));
        model.addAttribute("member",sessionMember);
        return "/board/comment/commentContainer :: #commentContainer";
    }

    //댓글 삭제
    @DeleteMapping("/comment/delete")
    public String delComment(@RequestParam Long commentSeq,HttpSession session, Model model){
        SessionMember sessionMember = (SessionMember) session.getAttribute("member");
        Long boardSeq = commentService.removeComment(commentSeq);
        model.addAttribute("selectedBoard",boardService.getBoard(boardSeq));
        model.addAttribute("member",sessionMember);
        return "/board/comment/commentContainer :: #commentContainer";
    }

    //댓글 수정
    @PutMapping("/comment/update")
    public String editComment(@RequestParam Long commentSeq,CommentDto commentDto,HttpSession session, Model model){
        SessionMember sessionMember = (SessionMember) session.getAttribute("member");
        commentService.updateComment(commentSeq,commentDto);
        model.addAttribute("selectedBoard",boardService.getBoard(commentDto.getBoardSeq()));
        model.addAttribute("member",sessionMember);
        return "/board/comment/commentContainer :: #commentContainer";
    }

    //대댓글 생성
    @PostMapping("comment/reply{seq}")
    public ModelAndView replyComment(Long seq, @Valid CommentDto commentDto,HttpSession session){
        SessionMember sessionMember = (SessionMember) session.getAttribute("member");
        Long boardSeq = commentService.addReply(seq,sessionMember,commentDto);
        return new ModelAndView("redirect:/board/view?seq="+boardSeq);
    }

//    //댓글 수정
//    @PostMapping("comment/update{seq}")
//    public ModelAndView updateComment(Long seq, String commentContent){
//        long boardSeq = commentService.updateComment(seq, commentContent);
//        return new ModelAndView("redirect:/board/view?seq="+boardSeq);
//    }

}
