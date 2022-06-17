package com.example.boardpractice.domain;

import com.example.boardpractice.dto.BoardDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Member member;  //글쓴이 유저

    @OneToMany(mappedBy = "board")
    @OrderBy("seq asc")
    private List<Comment> comments = new ArrayList<>();//댓글

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

    public void setMember(Member member){
        this.member = member;
    }

    //댓글 추가
    public void addComment(Comment comment){
        if(this.comments == null) comments = new ArrayList<>();
        this.comments.add(comment);
        comment.setBoard(this);
    }
}
