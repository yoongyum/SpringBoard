package com.example.boardpractice.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
    
    @Id//Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;  //고유 번호

    @Column(length= 20, unique = true)
    private String id;  //회원 아이디 varchar(20)

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime; //생성 날짜
}
