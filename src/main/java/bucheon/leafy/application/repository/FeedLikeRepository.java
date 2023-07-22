package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feed.FeedLikeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface FeedLikeRepository extends JpaRepository<FeedLikeCount, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<FeedLikeCount> findByFeedId(Long feedId);

}
