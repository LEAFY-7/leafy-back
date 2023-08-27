package bucheon.leafy.application.repository;

import bucheon.leafy.domain.qna.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {
    List<Qna> findTop10ByUserIdOrderByCreatedAtDesc(Long userId);
}
