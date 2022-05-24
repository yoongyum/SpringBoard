package com.example.boardpractice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {
    private Long seq;
    private String id;
    private String password;
    private String name;
    private String role;
    private String intro;
    private String age;
    private LocalDateTime createDate;
}
