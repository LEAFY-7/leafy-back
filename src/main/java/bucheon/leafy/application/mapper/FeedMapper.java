package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.feed.response.FeedResponse;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
@Mapper
public interface FeedMapper {

    List<FeedResponse> findFeedListFirst();

    List<FeedResponse> findFeedListScroll(Long feedId);

    FeedResponse findFeedById(Long feedId);

    Long saveFeed(FeedRequest request);

    int editFeed(FeedRequest request);

    void deleteAllFeeds();

    void hardDeleteFeed(Long feedId);

    int softDeleteFeed(Long feedId);
}
