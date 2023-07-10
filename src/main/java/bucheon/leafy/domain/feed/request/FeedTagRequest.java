package bucheon.leafy.domain.feed.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class FeedTagRequest {
    private Long feed_id;
    private List<String> tag;

    @Builder
    private FeedTagRequest(Long feed_id, List<String> tag) {
        this.feed_id = feed_id;
        this.tag = tag;
    }
}
