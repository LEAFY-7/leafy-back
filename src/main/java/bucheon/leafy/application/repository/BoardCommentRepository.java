package bucheon.leafy.application.repository;

import bucheon.leafy.domain.board.Board;
import bucheon.leafy.domain.board.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

}
