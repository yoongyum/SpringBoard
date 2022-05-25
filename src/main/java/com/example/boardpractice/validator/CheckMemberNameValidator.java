package com.example.boardpractice.validator;

import com.example.boardpractice.dto.MemberDto;
import com.example.boardpractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckMemberNameValidator extends AbstractValidator<MemberDto>{

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberDto dto, Errors errors) {
        if(memberRepository.existsMemberByName(dto.getName())){
            errors.rejectValue("name","닉네임 중복 오류", "이미 사용중인 닉네임입니다.");
        }
    }
}
