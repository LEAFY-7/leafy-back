package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.board.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 게시글 리스트
    public List<Board> findBoardList();

    // 게시글 상세조회
    public Board findBoardById(Long id);

    // 게시글 등록
    public Board saveBoard(Board board);

    // 게시글 수정
    public int editBoard(Board board);

    // 게시글 삭제
    public int deleteBoard(Long id);
}
