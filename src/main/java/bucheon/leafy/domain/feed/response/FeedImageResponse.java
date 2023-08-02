package bucheon.leafy.domain.feed.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedImageResponse {
    private Long feedImageId;
    private String imageName;
    private String imageUrl;

    @Builder
    public FeedImageResponse(Long feedImageId, String imageName) {
        this.feedImageId = feedImageId;
        this.imageName = imageName;
    }
}
