package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.dto.request.FeedImageRequest;
import bucheon.leafy.domain.feed.dto.response.FeedImageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedImageMapper {
    List<FeedImageResponse> findImageList(Long feedId);

    void saveImage(List<FeedImageRequest> requestList);

    void deleteAllImages();

    void deleteImage(Long imageId);
}
