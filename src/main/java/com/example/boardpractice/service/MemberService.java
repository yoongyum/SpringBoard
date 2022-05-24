package com.example.boardpractice.service;

import com.example.boardpractice.domain.Member;
import com.example.boardpractice.dto.MemberDto;
import com.example.boardpractice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Member insertMember(MemberDto memberDto){
        return memberRepository.save(Member
                .builder(memberDto)
                .build());
    }

}
