package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feed.FeedLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedLikeRepository extends JpaRepository<FeedLike, Long> {

}
