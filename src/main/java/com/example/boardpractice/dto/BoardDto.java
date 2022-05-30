package com.example.boardpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDto {
    private Long seq;
    private String title;
    private String author;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private Long views;
}
