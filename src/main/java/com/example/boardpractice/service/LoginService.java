package com.example.boardpractice.service;

import com.example.boardpractice.dto.LoginForm;
import com.example.boardpractice.domain.Member;
import com.example.boardpractice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class LoginService {
    private MemberRepository memberRepository;

    @Autowired
    public LoginService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /*
        로그인 체크
    */
    public boolean checkMember(LoginForm loginForm){
        Optional<Member> res = memberRepository.findByEmail(loginForm.getEmail());

        //이메일에 따른 사용자 추출성공 후 사용자 비밀번호 일치 확인
        if(res.isPresent() && res.get().getPassword().equals(loginForm.getPassword())){
            return true;
        }
        return false;
    }

}
