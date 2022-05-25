package com.example.boardpractice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {
    private Long seq;
    private String email;
    private String password;
    private String name;
    private String role;
    private String intro;
    private String age;
    private LocalDateTime createDate;
}
