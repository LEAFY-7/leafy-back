package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.board.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 피드 리스트
    public List<Board> findBoardList();

    // 피드 상세조회
    public Board findBoardById(Long id);

    // 피드 등록
    public Board saveBoard(Board board);

    // 피드 수정
    public Board editBoard(Board board);

    // 피드 삭제
    public int deleteBoard(Long id);
}
