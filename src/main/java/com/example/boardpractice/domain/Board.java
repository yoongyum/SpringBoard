package com.example.boardpractice.domain;

import com.example.boardpractice.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder(builderMethodName = "BoardBuilder")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String title;

    private String author;  //글쓴이 유저 네임

    private String content; //글 내용

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createDate;   //생성 날짜

    private LocalDateTime modifiedDate; //최종 수정 날짜
    
    private Long views; //조회수

    public static BoardBuilder builder(BoardDto boardDto){
        return BoardBuilder()
                .title(boardDto.getTitle())
                .author(boardDto.getAuthor())
                .content(boardDto.getContent())
                .createDate(LocalDateTime.now())
                .views(0L);
    }
}
