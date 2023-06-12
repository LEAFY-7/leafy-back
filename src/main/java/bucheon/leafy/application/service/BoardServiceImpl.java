package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.BoardMapper;
import bucheon.leafy.domain.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private BoardMapper boardMapper;

    @Override
    public List<Board> getBoardList() {
        return boardMapper.findBoardList();
    }

    @Override
    public Board getBoardById(Long id) {
        return boardMapper.findBoardById(id);
    }

    @Override
    public Board saveBoard(Board board) {
        Board savedBoard =  boardMapper.saveBoard(board);

        return savedBoard;
    }

    @Override
    public Board updateBoard(Board board) {
        Board updatedBoard = boardMapper.editBoard(board);
        return updatedBoard;
    }

    @Override
    public boolean deleteBoard(Long id) {
        return boardMapper.deleteBoard(id) == 1;
    }
}
