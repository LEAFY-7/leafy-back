package bucheon.leafy.domain.feed.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedImageRequest {
    private Long feedId;
    private String imageName;

    @Builder
    private FeedImageRequest(Long feedId, String imageName) {
        this.feedId = feedId;
        this.imageName = imageName;
    }
}
