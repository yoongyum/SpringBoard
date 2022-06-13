package com.example.boardpractice.validator;

import com.example.boardpractice.auth.dto.SessionMember;
import com.example.boardpractice.dto.MemberDto;
import com.example.boardpractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckMemberEmailValidator extends AbstractValidator<SessionMember>{

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(SessionMember dto, Errors errors) {
        if(memberRepository.existsMemberByEmail(dto.getEmail())){
            errors.rejectValue("email","이메일 중복 오류", "이미 사용중인 이메일 주소 입니다.");
        }
    }
}
