package bucheon.leafy.domain.feed.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedImageResponse {
    private Long image_id;
    private String image_name;
    private String image_path;
    private Boolean is_thumb;
    private String imageUrl;

    @Builder
    private FeedImageResponse(Long image_id, String image_name, String image_path, Boolean is_thumb, String imageUrl) {
        this.image_id = image_id;
        this.image_name = image_name;
        this.image_path = image_path;
        this.is_thumb = is_thumb;
        this.imageUrl = "default";
    }
}
