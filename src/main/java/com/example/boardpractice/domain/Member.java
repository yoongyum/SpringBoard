package com.example.boardpractice.domain;

import com.example.boardpractice.auth.dto.SessionMember;
import com.example.boardpractice.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder(builderMethodName = "MemberBuilder")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
    
    @Id//Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;  //고유 번호

    @Column(length= 50, unique = true)
    private String email;  //회원 아이디 varchar(50)

//    @Column(length = 13, nullable = false)
//    private String password;    //회원 비밀번호 varchar(13)

    @Column(length = 20, nullable = false)
    private String name;    //회원 이름 varchar(20)

    @Enumerated
    @Column(nullable = false)
    private Role role;    //권한 USER or ADMIN

    @Column(length = 200)
    private String intro;   //자기소개 varchar(200)

    @Column
    private String picture; //프로필 사진

    @Column
    private String age;     //나이대

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate; //생성 날짜

//                .createDate(LocalDateTime.now());

    public static MemberBuilder builder(SessionMember sessionMember){
        return MemberBuilder()
                .name(sessionMember.getName())
                .email(sessionMember.getEmail())
                .picture(sessionMember.getPicture())
                .role(Role.USER);
    }

    //회원 정보 수정
    public Member updateInfo(String name, String intro){
        this.name = name;
        this.intro = intro;
        return this;
    }
    public String getRoleKey(){
        return this.role.getKey();
    }
}
