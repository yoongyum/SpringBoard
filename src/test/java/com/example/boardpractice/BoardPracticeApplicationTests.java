package com.example.boardpractice;

import com.example.boardpractice.auth.dto.SessionMember;
import com.example.boardpractice.domain.Board;
import com.example.boardpractice.domain.Comment;
import com.example.boardpractice.domain.Member;
import com.example.boardpractice.domain.Role;
import com.example.boardpractice.dto.BoardDto;
import com.example.boardpractice.dto.CommentDto;
import com.example.boardpractice.repository.BoardRepository;
import com.example.boardpractice.repository.CommentRepository;
import com.example.boardpractice.repository.MemberRepository;
import com.example.boardpractice.service.BoardService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Transactional
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class BoardPracticeApplicationTests {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BoardService boardService;



//    @Test
    @Order(2)
    public void selectDummies(){//Read 테스트
        Optional<Member> res = memberRepository.findByName("usr1");
        System.out.println("=====================");

        if (res.isPresent()){
            Member member = res.get();
            System.out.println(member);
        }

    }

//    @Test
    public void DeleteDummies(){    //303번 회원 삭제
        Optional<Member> res = memberRepository.findById(305L);
        res.ifPresent(selectMember->{
            memberRepository.delete(selectMember);
        });
    }

//    @Test
    public void getBoard(){
        System.out.println(boardService.getBoard(31L).toString());
    }

    // 게시글 삭제
//    @Test
    public void deleteBoard(){
        boardService.deleteBoard(41L);
    }

//    @Test
    public void cascadeTest(){
        SessionMember sessionMember = new SessionMember();
        sessionMember.setName("test1");
        sessionMember.setEmail("test@email.com");
        sessionMember.setRole(Role.USER);
        Member member1 = Member.builder(sessionMember).build();
        BoardDto boardDto1 = new BoardDto();
        boardDto1.setTitle("아무글");

        BoardDto boardDto2 = new BoardDto();
        boardDto2.setTitle("아무글2");

        Board board1 = Board.builder(boardDto1).build();
        Board board2 = Board.builder(boardDto2).build();

        member1.addBoard(board1);
        member1.addBoard(board2);

        memberRepository.save(member1);
        memberRepository.delete(member1);

        List<Board> boards = boardRepository.findAll();
        for (Board board : boards) {
            System.out.println(board.getTitle());
        }
    }

    @Test
    public void commentTest(){
        
        //멤버생성 (게시글 주인)
        SessionMember sessionMember = new SessionMember();
        sessionMember.setName("test1");
        sessionMember.setEmail("test@email.com");
        sessionMember.setRole(Role.USER);
        Member member1 = Member.builder(sessionMember).build();
        memberRepository.save(member1);

        //멤버생성2 (댓글러)
        SessionMember sessionMember2 = new SessionMember();
        sessionMember2.setName("member2");
        sessionMember2.setEmail("test2@email.com");
        sessionMember2.setRole(Role.USER);
        Member member2 = Member.builder(sessionMember2).build();
        memberRepository.save(member2);

        //게시글 생성
        BoardDto boardDto1 = new BoardDto();
        boardDto1.setTitle("댓글 테스트용 글");

        Board board1 = Board.builder(boardDto1).build();
        member1.addBoard(board1);
        boardRepository.save(board1);

        //댓글 생성
        CommentDto commentDto = new CommentDto();
        commentDto.setContent("댓글");
        Comment comment = Comment.builder(commentDto).build();
        comment.setContent("첫 댓글!!");

        //멤버2 board1에 댓글 등록
        member2.addComment(comment,board1);
        commentRepository.save(comment);

        memberRepository.delete(member2);


        var res = boardRepository.findBySeq(board1.getSeq());
        res.ifPresent(board -> {
            System.out.println(board.getTitle());
            for (Comment boardComment : board.getComments()) {
                System.out.println(boardComment.getMember().getName());
                System.out.println(boardComment.getContent());
            }
        });
    }
}
