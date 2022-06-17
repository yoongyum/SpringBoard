package com.example.boardpractice.dto;

import com.example.boardpractice.auth.dto.SessionMember;
import com.example.boardpractice.domain.Comment;
import com.example.boardpractice.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDto {
    private Long seq;
    private String title;
    private Member member;
    private List<Comment> comments = new ArrayList<>();
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private int views;
}
