package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.feedLikeInfo.FeedLikeInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedLikeInfoRepository extends JpaRepository<FeedLikeInfo, Long> {

    Optional<FeedLikeInfo> findByUserAndFeed(User user, Feed feed);

    @EntityGraph( attributePaths = { "feed", "feed.feedLikeCount", "feed.feedImages" } )
    List<FeedLikeInfo> findAllWithFeedByUserId(Long userId, Pageable pageable);
}

