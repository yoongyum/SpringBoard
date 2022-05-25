package com.example.boardpractice.domain;

import com.example.boardpractice.dto.MemberDto;
import lombok.*;
import org.springframework.core.annotation.Order;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder(builderMethodName = "MemberBuilder")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
    
    @Id//Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;  //고유 번호

    @Column(length= 50, unique = true)
    private String email;  //회원 아이디 varchar(20)

    @Column(length = 13, nullable = false)
    private String password;    //회원 비밀번호 varchar(13)

    @Column(length = 10, nullable = false)
    private String name;    //회원 이름 varchar(10)

    @Column
    private String role;    //권한 USER or ADMIN

    @Column(length = 200)
    private String intro;   //자기소개 varchar(200)

    @Column
    private String age;     //나이대

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createDate; //생성 날짜

    public static MemberBuilder builder(MemberDto memberDto){
        return MemberBuilder()
                .seq(memberDto.getSeq())
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .name(memberDto.getName())
                .role(memberDto.getRole())
                .intro(memberDto.getIntro())
                .age(memberDto.getAge())
                .createDate(LocalDateTime.now());
    }
}
