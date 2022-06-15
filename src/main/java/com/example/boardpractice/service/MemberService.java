package com.example.boardpractice.service;

import com.example.boardpractice.auth.dto.SessionMember;
import com.example.boardpractice.domain.Member;
import com.example.boardpractice.dto.MemberDto;
import com.example.boardpractice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
            회원가입
    */
//    public Member insertMember(SessionMember memberDto){
//        memberDto.setPicture("/asset/img/defaultPicture.png");//기본이미지 설정
//        return memberRepository.save(Member
//                .builder(memberDto)
//                .build());
//    }
//
//    //회원가입 유효성 체크
//    public Map<String, String> validationHandler(Errors errors){
//        Map<String, String> validator = new HashMap<>();
//
//        for(FieldError error: errors.getFieldErrors()){
//            //key = valid_{Dto 필드명}
//            String key = String.format("valid_%s", error.getField());
//            validator.put(key, error.getDefaultMessage());
//        }
//        return validator;
//    }

    //유저 정보 수정
    public Member updateInfo(SessionMember sessionMember){
        Member member = memberRepository.findByEmail(sessionMember.getEmail())
                .map(entity -> entity.updateInfo(sessionMember.getName(), sessionMember.getIntro()))
                .orElse(null);

        return memberRepository.save(member);
    }

    public void deleteMember(SessionMember sessionMember){
        Member member = memberRepository.findByEmail(sessionMember.getEmail()).orElse(null);

        assert member != null;
        memberRepository.delete(member);
    }
}
