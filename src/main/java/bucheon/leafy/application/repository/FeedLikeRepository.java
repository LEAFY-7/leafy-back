package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feed.FeedLikeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface FeedLikeRepository extends JpaRepository<FeedLikeCount, Long> {

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<FeedLikeCount> findByFeedId(Long feedId);

}
