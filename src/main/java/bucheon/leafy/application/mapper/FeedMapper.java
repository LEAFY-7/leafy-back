package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.request.FeedRequest;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Optional;

@MapperScan
@Mapper
public interface FeedMapper {

    List<Feed> findFeedListFirst();

    List<Feed> findFeedListScroll(Long id);

    // 사용자가 특정 feed_id로 조회했을 시 해당 feed가 없을수도 있기때문에 optional 사용
    Optional<Feed> findFeedById(Long id);

    Long saveFeed(FeedRequest feed);

    void editFeed(FeedRequest feed);

    void hardDeleteFeed(Long id);
    int softDeleteFeed(Long id);
}
