package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feed.FeedTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedTagRepository extends JpaRepository<FeedTag, Long> {

}
