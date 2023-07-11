package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.dto.request.FeedTagRequest;
import bucheon.leafy.domain.feed.dto.response.FeedTagResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedTagMapper {
    List<FeedTagResponse> findTagList();

    Long saveTag(FeedTagRequest request);

    void deleteTag();
}
