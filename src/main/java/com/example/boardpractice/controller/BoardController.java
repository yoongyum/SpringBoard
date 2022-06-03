package com.example.boardpractice.controller;

import com.example.boardpractice.auth.dto.SessionMember;
import com.example.boardpractice.dto.BoardDto;
import com.example.boardpractice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final HttpSession httpSession;
    private final BoardService boardService;

//    @Autowired
//    public BoardController(BoardService boardService) {
//        this.boardService = boardService;
//    }

    @GetMapping("/")
    public ModelAndView list(Model model,HttpServletRequest request,HttpServletResponse response){
        showBoardList(model);

        model.addAttribute("containerState","list");
        //세션에 있는 회원정보 가져오기
        checkSessionMember(model);
        return new ModelAndView("/index");
    }
    
    @PostMapping("/insert")//글쓰기 버튼 클릭 시
    public ModelAndView insertBoard(BoardDto boardDto, Errors errors, Model model){
        //게시글 추가
        boardService.addPost(boardDto,httpSession);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/post")//글쓰러가기 누를 시
    public ModelAndView posting(Model model,HttpServletRequest request){
        model.addAttribute("containerState","post");
        checkSessionMember(model);
        return new ModelAndView("/index");
    }


    //특정 게시물 보기
    @GetMapping("/board/view{seq}")
    public ModelAndView viewBoard(Long seq, Model model,HttpServletRequest request){
        model.addAttribute("selectedBoard", boardService.getBoard(seq));
        model.addAttribute("containerState","view");
        checkSessionMember(model);
        return new ModelAndView("/index");
    }

    //특정 게시물 삭제
    @GetMapping("/board/view/delete{seq}")
    public ModelAndView deleteBoard(Long seq, Model model){
        boardService.deleteBoard(seq);
        return new ModelAndView("redirect:/");
    }


    public void checkSessionMember(Model model){
        SessionMember member = (SessionMember) httpSession.getAttribute("member");
        if(member != null){
            System.out.println("로그인 중인 멤버 발견 성공!");
            model.addAttribute("member", member);
        }else{
            System.out.println("로그인 중인 멤버 발견 실패");
            model.addAttribute("member",null);
        }
    }

    //전체 게시글리스트 전달
    private void showBoardList(Model model){
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("boardList", boardDtoList);
    }
}
