package com.example.boardpractice.controller;

import com.example.boardpractice.dto.LoginForm;
import com.example.boardpractice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping("/login")
    public ModelAndView login(LoginForm loginForm, Model model){
        if(!loginService.checkMember(loginForm)){
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("/login/prof");
    }
}