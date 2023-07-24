package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.request.FeedImageRequest;
import bucheon.leafy.domain.feed.response.FeedImageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedImageMapper {

    List<FeedImageResponse> findImageList(Long feedId);

    String findImage(Long imageId);

    void saveImage(@Param("feedId") Long feedId, @Param("request") List<FeedImageRequest> request);

    void deleteAllImages();

    int deleteImage(Long feedId, Long imageId);
}