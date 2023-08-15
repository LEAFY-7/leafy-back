package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.util.request.ScrollRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedList;
import java.util.List;

@Mapper
public interface FeedMapper {

    LinkedList<FeedResponse> findFeedListFirst(ScrollRequest scrollRequest);

    LinkedList<FeedResponse> findFeedList(ScrollRequest scrollRequest);

    FeedResponse findFeedById(Long feedId);

    Long saveFeed(@Param("userId") Long userId, @Param("request") FeedRequest request);

    int editFeed(@Param("feedId") Long feedId, @Param("userId") Long userId, FeedRequest request);

    void deleteAllFeeds();

    int deleteFeed(Long feedId, Long userId);
}
