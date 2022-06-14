package com.example.boardpractice.domain;

import com.example.boardpractice.auth.dto.SessionMember;
import com.example.boardpractice.dto.BoardDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder(builderMethodName = "BoardBuilder")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id")
    private Member member;  //글쓴이 유저

    private String content; //글 내용

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createDate;   //생성 날짜

    private LocalDateTime modifiedDate; //최종 수정 날짜

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int views; //조회수

    public static BoardBuilder builder(BoardDto boardDto){
        return BoardBuilder()
                .title(boardDto.getTitle())
                .member(boardDto.getMember())
                .content(boardDto.getContent())
                .createDate(LocalDateTime.now());
    }

    public void increaseViews(){
        this.views++;
    }
}
