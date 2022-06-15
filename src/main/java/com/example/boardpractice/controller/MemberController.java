package com.example.boardpractice.controller;

import com.example.boardpractice.auth.dto.SessionMember;
import com.example.boardpractice.dto.MemberDto;
import com.example.boardpractice.service.MemberService;
import com.example.boardpractice.validator.CheckMemberEmailValidator;
import com.example.boardpractice.validator.CheckMemberNameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
//    private final CheckMemberEmailValidator checkMemberEmailValidator;
//    private final CheckMemberNameValidator checkMemberNameValidator;
    private final HttpSession httpSession;

//    @InitBinder
//    public void validatorBinder(WebDataBinder binder){
//        binder.addValidators(checkMemberEmailValidator);
//        binder.addValidators(checkMemberNameValidator);
//    }

//    @GetMapping("/member/insertMember")
//    public ModelAndView create(@Valid SessionMember memberDto, Errors errors ,Model model) {
//        model.addAttribute("memberDto", memberDto);
//        return new ModelAndView("/member/insertMember");
//    }


    //회원가입
//    @PostMapping("/member/insertMember/register")
//    public ModelAndView insertMember(@Valid SessionMember memberDto, Errors errors, Model model) {
//        if (errors.hasErrors()) {
//            //회원가입 실패 시 입력 데이터값을 유지
//            model.addAttribute("memberDto", memberDto);
//            //유효성 통과 못한 필드와 에러메세지 핸들링
//            Map<String, String> validationResult = memberService.validationHandler(errors);
//
//            for (String key : validationResult.keySet()) {//key : 메세지 -> 파라미터로 전달
//                model.addAttribute(key, validationResult.get(key));
//            }
//
//            //회원가입 페이지로 다시 보내기
//            return new ModelAndView("/member/insertMember");
//        }
//
//        System.out.println("회원가입 성공!!");
//        memberService.insertMember(memberDto);
//        return new ModelAndView("redirect:/");
//    }

    //회원 상세정보
    @GetMapping("/member/info")
    public ModelAndView goInfo(Model model){
        SessionMember sessionMember = (SessionMember) httpSession.getAttribute("member");
        model.addAttribute("member", sessionMember);
        return new ModelAndView("/member/info");
    }

    //회원 수정
    @PostMapping("/member/edit")
    public ModelAndView doEdit(@RequestParam("name") String name, @RequestParam("intro") String intro){
        SessionMember sessionMember = (SessionMember) httpSession.getAttribute("member");
        sessionMember.setName(name);
        sessionMember.setIntro(intro);
        memberService.updateInfo(sessionMember);
        return new ModelAndView("redirect:/member/info");
    }

    //회원 탈퇴
    @GetMapping("/member/delete")
    public ModelAndView doDelete(){
        SessionMember sessionMember = (SessionMember) httpSession.getAttribute("member");
//        memberService.deleteMember(sessionMember);
        //회원 탈퇴시 자동으로 로그아웃
        return new ModelAndView("redirect:/logout");
    }

}
