package com.example.boardpractice;

import com.example.boardpractice.domain.Member;
import com.example.boardpractice.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.stream.IntStream;

@Rollback(true)
@SpringBootTest
class BoardPracticeApplicationTests {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insertDummies() {//회원 등록
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Member member = Member.builder()
                    .id("GuestUser" + i)
                    .password("1234")
                    .name("usr" + i)
                    .role("USER")
                    .intro("잘부탁드립니다.." + i)
                    .age("20대")
                    .datetime(new Date())
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    public void SelectDummies(){//Read 테스트
        Long seq = 66L;
        Optional<Member> res = memberRepository.findBySeq(seq);

        System.out.println("=====================");

        if (res.isPresent()){
            Member member = res.get();
            System.out.println(member);
        }

    }

}
