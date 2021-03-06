package com.example.boardpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    Long boardSeq; //댓글이 위치한 게시글 번호
    
    @NotBlank(message = "댓글에 아무 내용이 없습니다.")
    String commentContent;
    private LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime modifiedDate;
}
