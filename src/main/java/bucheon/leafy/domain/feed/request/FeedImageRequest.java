package bucheon.leafy.domain.feed.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedImageRequest {
    @JsonIgnore
    private Long feedImageId;
    private String imageName;

    @Builder
    private FeedImageRequest(Long feedImageId, String imageName) {
        this.feedImageId = feedImageId;
        this.imageName = imageName;
    }
}
