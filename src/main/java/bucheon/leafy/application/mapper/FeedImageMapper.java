package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.request.FeedImageRequest;
import bucheon.leafy.domain.feed.response.FeedImageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedImageMapper {
    List<FeedImageResponse> findFeedImageList(Long feedId);

    void saveFeedImage(@Param("feedId") Long feedId, @Param("request") List<FeedImageRequest> request);
    
    void deleteFeedImage(Long feedId);

    List<FeedImageResponse> findFeedImagesByFeedId(@Param("feedIds") List<Long> feedIds);
}