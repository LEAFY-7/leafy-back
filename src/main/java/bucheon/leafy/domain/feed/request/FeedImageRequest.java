package bucheon.leafy.domain.feed.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedImageRequest {
    @JsonIgnore
    private Long imageId;
    private String imageName;

    @Builder
    private FeedImageRequest(Long imageId, String imageName) {
        this.imageId = imageId;
        this.imageName = imageName;
    }
}
