package com.example.boardpractice.domain;

import com.example.boardpractice.dto.CommentDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_seq")
    private Comment parent; //댓글의 부모

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createDate;   //생성 날짜


    //빌더
    public static CommentBuilder builder (CommentDto dto){
        return CommentBuilder()
                .content(dto.getContent())
                .createDate(dto.getCreateDate());
    }

    //내용 수정
    public void editContent(String content) {
        this.content = content;
    }

    //대댓글 추가
    public void addReply(Comment child) {
        this.children.add(child);
        child.setParent(this);
    }

    
    //댓글 작성자 추가
    public void setMember(Member member){
        this.member = member;
    }
    
    //댓글이 있는 게시글 추가
    public void setBoard(Board board){
        this.board = board;
    }

    //대댓글 parent와 게시글 등록
    public void setParent(Comment parent){
        this.parent = parent;
        this.board = parent.getBoard();
    }
}
