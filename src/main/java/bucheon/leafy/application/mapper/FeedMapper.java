package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.dto.request.FeedRequest;
import bucheon.leafy.domain.feed.dto.response.FeedResponse;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Optional;

@MapperScan
@Mapper
public interface FeedMapper {

    List<FeedResponse> findFeedListFirst();

    List<FeedResponse> findFeedListScroll(Long id);

    // 사용자가 특정 feed_id로 조회했을 시 해당 feed가 없을수도 있기때문에 optional 사용
    Optional<FeedResponse> findFeedById(Long id);

    // insert 후 auto-incremented 필드값이 반환됨
    Long saveFeed(FeedRequest request);

    int editFeed(FeedRequest request);

    void deleteAllFeeds();

    void hardDeleteFeed(Long id);
    int softDeleteFeed(Long id);
}
