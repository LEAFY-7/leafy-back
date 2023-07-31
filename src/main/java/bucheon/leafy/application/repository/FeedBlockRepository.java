package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feedblock.FeedBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBlockRepository extends JpaRepository<FeedBlock, Long> {

}
