package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feedlikeinfo.FeedLikeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedLikeInfoRepository extends JpaRepository<FeedLikeInfo, Long> {

    Optional<FeedLikeInfo> findByUserIdAndFeedId(Long userId, Long feedId);

    @EntityGraph( attributePaths = { "feed", "feed.feedLikeCount", "feed.feedImages" } )
    Page<FeedLikeInfo> findAllWithFeedByUserId(Long userId, Pageable pageable);

    Long countByUserId(Long userId);
}
