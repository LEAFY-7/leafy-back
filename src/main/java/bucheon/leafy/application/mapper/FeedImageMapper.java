package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.request.FeedImageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedImageMapper {

    List<String> findImageList(Long feedId);

    void saveImage(List<FeedImageRequest> request);

    void deleteAllImages();

    int deleteImage(Long feedId, String imageName);
}