package com.example.boardpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {

    @GetMapping("member/insertMember")
    public ModelAndView create(){
        return new ModelAndView("member/insertMember");
    }
}
