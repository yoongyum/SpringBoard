package com.example.boardpractice.controller;

import com.example.boardpractice.dto.MemberDto;
import com.example.boardpractice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/member/insertMember")
    public ModelAndView create(){
        return new ModelAndView("/member/insertMember");
    }

    
    //회원가입
    @PostMapping("/member")
    public ModelAndView insertMember(MemberDto memberDto){
        memberService.insertMember(memberDto);
        return new ModelAndView("redirect:");
    }
}
