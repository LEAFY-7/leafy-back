package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.request.FeedTagRequest;
import bucheon.leafy.domain.feed.response.FeedTagResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedTagMapper {
    List<FeedTagResponse> findTagList(Long feedId);

    void saveTag(@Param("feedId") Long feedId, @Param("request") List<FeedTagRequest> request);

    int deleteTagNotIn(@Param("feedId") Long feedId, @Param("request") List<FeedTagRequest> request);
}
