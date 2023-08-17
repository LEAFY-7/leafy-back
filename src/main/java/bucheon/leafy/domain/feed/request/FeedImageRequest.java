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
    private Integer imageHeight;

    @Builder
    private FeedImageRequest(Long feedImageId, String imageName, Integer imageHeight) {
        this.feedImageId = feedImageId;
        this.imageName = imageName;
        this.imageHeight = imageHeight;
    }
}
