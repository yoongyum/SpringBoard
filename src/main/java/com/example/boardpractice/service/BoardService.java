package com.example.boardpractice.service;

import com.example.boardpractice.domain.Board;
import com.example.boardpractice.dto.BoardDto;
import com.example.boardpractice.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }
    /*
        게시글 추가
    */
    public Board addPost(BoardDto boardDto){
        return boardRepository.save(Board
                .builder(boardDto)
                .build());
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
                            .author(board.getAuthor())
                            .createDate(board.getCreateDate())
                            .modifiedDate(board.getModifiedDate())
                            .views(board.getViews())
                            .build());
        }
        return boardDtoList;
    }
}
