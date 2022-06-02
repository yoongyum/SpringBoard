package com.example.boardpractice.service;

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
    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
            회원가입
    */
//    public Member insertMember(MemberDto memberDto){
//        return memberRepository.save(Member
//                .builder(memberDto)
//                .build());
//    }

    //회원가입 유효성 체크
    public Map<String, String> validationHandler(Errors errors){
        Map<String, String> validator = new HashMap<>();

        for(FieldError error: errors.getFieldErrors()){
            //key = valid_{Dto 필드명}
            String key = String.format("valid_%s", error.getField());
            validator.put(key, error.getDefaultMessage());
        }
        return validator;
    }

}
