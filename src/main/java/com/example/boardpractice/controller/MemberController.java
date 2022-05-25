package com.example.boardpractice.controller;

import com.example.boardpractice.dto.MemberDto;
import com.example.boardpractice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/insertMember")
    public ModelAndView create(@Valid MemberDto memberDto, Model model) {
        model.addAttribute("memberDto", memberDto);
        return new ModelAndView("/member/insertMember");
    }


    //회원가입
    @PostMapping("/member")
    public ModelAndView insertMember(@Valid MemberDto memberDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            //회원가입 실패 시 입력 데이터값을 유지
            model.addAttribute("memberDto", memberDto);
            System.out.println("오류 발생");
            //유효성 통과 못한 필드와 에러메세지 핸들링
            Map<String, String> validationResult = memberService.validationHandler(errors);

            for (String key : validationResult.keySet()) {//key : 메세지 -> 파라미터로 전달
                model.addAttribute(key, validationResult.get(key));
            }

            //회원가입 페이지로 다시 보내기
            return new ModelAndView("/member/insertMember");
        }

        //회원가입 유효성 통과시
        memberService.insertMember(memberDto);
        return new ModelAndView("redirect:");
    }
}
