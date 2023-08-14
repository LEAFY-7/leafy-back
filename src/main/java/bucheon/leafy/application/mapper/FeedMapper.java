package bucheon.leafy.application.mapper;

import bucheon.leafy.application.controller.request.FeedSaveRequest;
import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.util.request.ScrollRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface FeedMapper {

    List<FeedResponse> findFeedListFirst(ScrollRequest scrollRequest);

    List<FeedResponse> findFeedListScroll(ScrollRequest scrollRequest);

    FeedResponse findFeedById(Long feedId);

    Long saveFeed(@Param("userId") Long userId, @Param("request") FeedSaveRequest request);

    int editFeed(@Param("feedId") Long feedId, @Param("userId") Long userId, FeedRequest request);

    void deleteAllFeeds();

    int deleteFeed(Long feedId, Long userId);
}
