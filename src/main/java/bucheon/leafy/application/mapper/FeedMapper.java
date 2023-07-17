package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.util.request.ScrollRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
@Mapper
public interface FeedMapper {

    List<FeedResponse> findFeedListFirst(ScrollRequest scrollRequest);

    List<FeedResponse> findFeedListScroll(ScrollRequest scrollRequest);

    FeedResponse findFeedById(Long feedId);

    Long saveFeed(@Param("userId") Long userId, @Param("request") FeedRequest request);

    int editFeed(@Param("feedId") Long feedId, @Param("userId") Long userId, FeedRequest request);

    void deleteAllFeeds();

    int deleteFeed(@Param("feedId") Long feedId, @Param("userId") Long userId);
}
