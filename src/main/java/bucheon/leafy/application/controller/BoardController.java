package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 리스트
    @GetMapping("/list")
    public String getBoardList() {


        return "Hello~";
    }

    // 게시글 등록

    // 게시글 수정

    // 게시글 삭제
}
