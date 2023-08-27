package bucheon.leafy.domain.feed.response;

import lombok.Builder;
import lombok.Getter;

import static bucheon.leafy.path.S3Path.ABSOLUTE_PATH;
import static bucheon.leafy.path.S3Path.FEED_PATH;

@Getter
public class FeedImageResponse {
    private Long feedId;
    private Long feedImageId;
    private String image;
    private Integer imageHeight;

    @Builder
    public FeedImageResponse(Long feedId, Long feedImageId, String imageName, Integer imageHeight) {
        this.feedId = feedId;
        this.feedImageId = feedImageId;
        this.image = ABSOLUTE_PATH + FEED_PATH + imageName;
        this.imageHeight = imageHeight;
    }
}
