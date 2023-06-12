package bucheon.leafy.application.service;

import bucheon.leafy.domain.board.Board;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BoardService {

    // 피드 리스트
    public List<Board> getBoardList();

    // 피드 상세조회
    public Board getBoardById(Long id);

    // 피드 등록
    public Board saveBoard(Board board);

    // 피드 수정
    public Board updateBoard(Board board);

    // 피드 삭제
    public boolean deleteBoard(Long id);
}
