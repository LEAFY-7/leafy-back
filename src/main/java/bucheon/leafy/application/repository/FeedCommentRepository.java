package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feed.FeedComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCommentRepository extends JpaRepository<FeedComment, Long> {

}
