package bucheon.leafy.domain.feed.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedImageResponse {
    private Long imageId;
    private String imageName;
    private String imageUrl;

    @Builder
    public FeedImageResponse(Long imageId, String imageName) {
        this.imageId = imageId;
        this.imageName = imageName;
    }
}
