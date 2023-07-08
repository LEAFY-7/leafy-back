package bucheon.leafy.domain.feed.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class FeedImageRequest {
    private Long imageId;
    private Long feedId;
    private String imageName;
    private String imagePath;
    private Boolean isThumb;

    @Builder
    private FeedImageRequest(Long imageId, Long feedId, String imageName, String imagePath, Boolean isThumb) {
        this.imageId = imageId;
        this.feedId = feedId;
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.isThumb = isThumb;
    }
}
