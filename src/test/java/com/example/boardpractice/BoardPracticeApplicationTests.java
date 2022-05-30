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

import java.time.LocalDateTime;
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
    @Order(1)
    public void insertDummies() {//회원 등록
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Member member = Member.MemberBuilder()
                    .email("GuestUser" + i)
                    .password("1234")
                    .name("usr" + i)
                    .role("USER")
                    .intro("잘부탁드립니다.." + i)
                    .age("20대")
                    .createDate(LocalDateTime.now())
                    .build();
            memberRepository.save(member);
        });
    }

//    @Test
    @Order(2)
    public void selectDummies(){//Read 테스트
        Optional<Member> res = memberRepository.findByName("usr1");
        System.out.println("=====================");

        if (res.isPresent()){
            Member member = res.get();
            System.out.println(member);
        }

    }

//    @Test
    public void DeleteDummies(){    //303번 회원 삭제
        Optional<Member> res = memberRepository.findById(305L);
        res.ifPresent(selectMember->{
            memberRepository.delete(selectMember);
        });
    }
}
