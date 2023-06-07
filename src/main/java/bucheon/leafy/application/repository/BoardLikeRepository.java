package bucheon.leafy.application.repository;

import bucheon.leafy.domain.board.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

}
