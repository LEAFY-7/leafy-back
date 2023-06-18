package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feed.FeedLikeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface FeedLikeRepository extends JpaRepository<FeedLikeCount, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_READ)
    <S extends FeedLikeCount> S save(S entity);

}
