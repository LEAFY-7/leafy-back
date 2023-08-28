package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.response.FeedTagResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedTagMapper {
    List<FeedTagResponse> findFeedTags(Long feedId);

    void saveFeedTag(@Param("feedId") Long feedId, @Param("tagList") List<String> tagList);

    void deleteFeedTag(Long feedId);
}
