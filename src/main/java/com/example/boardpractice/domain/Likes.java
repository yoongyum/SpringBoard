package com.example.boardpractice.domain;

import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "board_seq")
    private Board board;    //좋아요의 게시글

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;  //좋아요 누른 멤버

}
