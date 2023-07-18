package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface FeedLikeRepository extends JpaRepository<FeedLikeCount, Long> {

    @Modifying
    @Transactional
    @Query(value = "update FeedLikeCount f set f.likeCount = f.likeCount + 1 where f.feed = :feed")
    void likeIncrease(@Param("feed") Feed feed);

    @Modifying
    @Transactional
    @Query(value = "update FeedLikeCount f set f.likeCount = f.likeCount - 1 where f.feed = :feed")
    void likeDecrease(@Param("feed") Feed feed);

}
