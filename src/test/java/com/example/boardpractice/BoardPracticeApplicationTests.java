package com.example.boardpractice;

import com.example.boardpractice.domain.Member;
import com.example.boardpractice.repository.MemberRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
//@Transactional
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class BoardPracticeApplicationTests {

    @Autowired
    MemberRepository memberRepository;

//    @Test
//    @Order(1)
//    public void insertDummies() {//회원 등록
//        IntStream.rangeClosed(1, 10).forEach(i -> {
//            Member member = Member.builder()
//                    .id("GuestUser" + i)
//                    .password("1234")
//                    .name("usr" + i)
//                    .role("USER")
//                    .intro("잘부탁드립니다.." + i)
//                    .age("20대")
//                    .datetime(new Date())
//                    .build();
//            memberRepository.save(member);
//        });
//    }

//    @Test
//    @Order(2)
//    public void selectDummies(){//Read 테스트
//        Optional<Member> res = memberRepository.findByName("usr1");
//        System.out.println("=====================");
//
//        if (res.isPresent()){
//            Member member = res.get();
//            System.out.println(member);
//        }
//
//    }

    @Test
    public void UpdateDummies(){//301번 멤버를 수정할 계획
        Optional<Member> res = memberRepository.findBySeq(301L);
        res.ifPresent(selectMember->{
            selectMember.setIntro("업데이트 확인");
            selectMember.setRole("ADMIN");
            memberRepository.save(selectMember);
        });

    }

}
