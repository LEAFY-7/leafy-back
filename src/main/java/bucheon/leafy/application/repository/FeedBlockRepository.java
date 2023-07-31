package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feedblock.FeedBlock;
import bucheon.leafy.domain.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedBlockRepository extends JpaRepository<FeedBlock, Long> {

    @EntityGraph(attributePaths = "feed")
    List<FeedBlock> findByUserId(Long userId, Pageable pageable);
    void deleteByUserAndFeed(User user, Feed feed);


}
