package com.example.boardpractice.service;

import com.example.boardpractice.auth.dto.SessionMember;
import com.example.boardpractice.domain.Board;
import com.example.boardpractice.domain.Likes;
import com.example.boardpractice.domain.Member;
import com.example.boardpractice.dto.BoardDto;
import com.example.boardpractice.repository.BoardRepository;
import com.example.boardpractice.repository.LikesRepository;
import com.example.boardpractice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final LikesRepository likesRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, MemberRepository memberRepository, LikesRepository likesRepository){
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
        this.likesRepository = likesRepository;
    }
    /*
        게시글 추가
    */
    public void addPost(BoardDto boardDto, HttpSession session){
        SessionMember member = (SessionMember) session.getAttribute("member");
        Member m = memberRepository.findByEmail(member.getEmail()).get();
        Board b = Board.builder(boardDto).build();
        m.addBoard(b);
        session.setAttribute("member", new SessionMember(m));

        member = (SessionMember) session.getAttribute("member");

        boardRepository.save(b);
    }
    /*
        전체 게시글 리스트 조회
    */
    public List<BoardDto> getBoardList(){
        List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "seq"));
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(Board board : boardList){
            boardDtoList.add(new BoardDto().builder()
                            .seq(board.getSeq())
                            .title(board.getTitle())
                            .member(board.getMember())
                            .createDate(board.getCreateDate())
                            .modifiedDate(board.getModifiedDate())
                            .views(board.getViews())
                            .build());
        }
        return boardDtoList;
    }
    /*
        특정 게시물 조회
    */
    public BoardDto getBoard(Long seq){
        Board board = boardRepository.findBySeq(seq).get();
        board.increaseViews();
        return new BoardDto().builder()
                .seq(board.getSeq())
                .title(board.getTitle())
                .member(board.getMember())
                .comments(board.getComments())
                .likes(board.getLikes())
                .content(board.getContent())
                .createDate(board.getCreateDate())
                .modifiedDate(board.getModifiedDate())
                .views(board.getViews())
                .build();
    }

    /*
        특정 게시물 삭제
    */
    public void deleteBoard(Long seq){
        Optional<Board> res = boardRepository.findBySeq(seq);
        res.ifPresent(boardRepository::delete);
    }

    //좋아요 추가
    public void addLikes(Long seq, SessionMember sessionMember) {
        Board board = boardRepository.findBySeq(seq).orElseThrow();

        Member member = memberRepository.findByEmail(sessionMember.getEmail()).orElseThrow();

        List<Likes> likesList = likesRepository.findAll();
        for (Likes likes : likesList) {
            //중복 체크
            if (member == likes.getMember() && board == likes.getBoard()){
                likesRepository.delete(likes);  //종복이면 좋아요 삭제
                return;
            }
        }
        Likes likes = new Likes();
        likes.setMemberAndBoard(member,board);

        likesRepository.save(likes);
    }
}
