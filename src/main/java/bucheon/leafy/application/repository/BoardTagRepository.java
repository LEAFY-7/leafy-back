package bucheon.leafy.application.repository;

import bucheon.leafy.domain.board.BoardTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTagRepository extends JpaRepository<BoardTag, Long> {

}
