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
public class CommentDto {
    String content;
    private LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime modifiedDate;
}
