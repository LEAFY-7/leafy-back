package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.BoardService;
import bucheon.leafy.domain.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 리스트
    @GetMapping
    public ResponseEntity<List<Board>> getBoardList() {
        List<Board> boardList = boardService.getBoardList();

        return ResponseEntity.ok().body(boardList);
    }

    // 특정 게시글
    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable("id") Long id) {
        Board board = boardService.getBoardById(id);

        return ResponseEntity.ok().body(board);
    }

    // 게시글 등록
    @PostMapping
    public ResponseEntity<Board> saveBoard(@RequestBody Board board) {
        Board savedBoard = boardService.saveBoard(board);

        return ResponseEntity.ok().body(savedBoard);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable("id") Long id, @RequestBody Board board) {
        Board updatedBoard = boardService.updateBoard(board);

        return ResponseEntity.ok().body(updatedBoard);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBoard(@PathVariable("id") Long id) {
        Boolean isDelete =  boardService.deleteBoard(id);

        return ResponseEntity.ok().body(isDelete);
    }
}
