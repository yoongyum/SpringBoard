package com.example.boardpractice.domain;

import com.example.boardpractice.dto.CommentDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "CommentBuilder")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;   //고유 번호

    @ManyToOne(optional = false)
    private Member member;//댓글 쓴사람


    @ManyToOne(optional = false)
    private Board board; //댓글 있는 게시판
    
    private String content;//댓글 내용

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createDate;   //생성 날짜

    public void setMember(Member member){
        this.member = member;
    }
    public void setBoard(Board board){
        this.board = board;
    }

    //빌더
    public static CommentBuilder builder (CommentDto dto){
        return CommentBuilder()
                .content(dto.getContent())
                .createDate(dto.getCreateDate());
    }
}
