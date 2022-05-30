package com.example.boardpractice.controller;

import com.example.boardpractice.dto.BoardDto;
import com.example.boardpractice.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public ModelAndView list(Model model){
        showBoardList(model);
        model.addAttribute("postBtn",false);
        return new ModelAndView("/index");
    }
    
    @PostMapping("/insert")//글쓰기 버튼 클릭 시
    public ModelAndView insertBoard(BoardDto boardDto, Errors errors, Model model){
        //게시글 추가
        boardService.addPost(boardDto);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/post")//글쓰러가기 누를 시
    public ModelAndView posting(Model model){
        model.addAttribute("postBtn",true);
        return new ModelAndView("/index");
    }

    //전체 게시글리스트 전달
    private void showBoardList(Model model){
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("boardList", boardDtoList);
    }
}
