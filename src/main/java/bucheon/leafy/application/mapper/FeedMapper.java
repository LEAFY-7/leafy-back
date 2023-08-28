package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.domain.user.response.FeedAuthorResponse;
import bucheon.leafy.util.request.ScrollRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Mapper
public interface FeedMapper {

    // 팔로워 10명의 최신 게시물을 조회한다.
    LinkedList<FeedResponse> findFeedsToFollowers(Long userId);
    LinkedList<FeedResponse> findFeedsNotInFollowersFeeds(@Param("feedIds") List<Long> feedIds,
                                                    @Param("size") Integer size);

    LinkedList<FeedResponse> findFeedsFirst(ScrollRequest scrollRequest);

    LinkedList<FeedResponse> findFeeds(ScrollRequest scrollRequest);

    Optional<FeedResponse> findFeedById(Long feedId);

    Long saveFeed(@Param("userId") Long userId, @Param("request") FeedRequest request);

    int editFeed(@Param("feedId") Long feedId, @Param("userId") Long userId, FeedRequest request);

    void deleteAllFeeds();

    int deleteFeed(Long feedId, Long userId);


    FeedAuthorResponse findUserByFeedId(Long feedId);

}
