package com.example.boardpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

    @PostMapping("/board")
    public ModelAndView create(){
        return new ModelAndView("/board/boardList");
    }
}
