package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feed.FeedLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface FeedLikeRepository extends JpaRepository<FeedLike, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_READ)
    <S extends FeedLike> S save(S entity);

}
