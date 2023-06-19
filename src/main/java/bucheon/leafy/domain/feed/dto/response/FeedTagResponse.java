package bucheon.leafy.domain.feed.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class FeedTagResponse {
    private Long tag_id;
    private Long feed_id;
    private List<String> tag;

    @Builder
    private FeedTagResponse(Long tag_id, Long feed_id, List<String> tag) {
        this.tag_id = tag_id;
        this.feed_id = feed_id;
        this.tag = tag;
    }
}
