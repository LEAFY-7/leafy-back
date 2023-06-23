package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.dto.request.FeedImageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedImageMapper {

    void saveImage(List<FeedImageRequest> requestList);

    void deleteAllImages();
}
