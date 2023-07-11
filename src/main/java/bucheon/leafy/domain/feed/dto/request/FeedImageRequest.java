package bucheon.leafy.domain.feed.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class FeedImageRequest {

    private Long image_id;
    private Long feed_id;
    private String image;

    @Builder
    private FeedImageRequest(Long image_id, Long feed_id, String image) {
        this.image_id = image_id;
        this.feed_id = feed_id;
        this.image = image;
    }
}
