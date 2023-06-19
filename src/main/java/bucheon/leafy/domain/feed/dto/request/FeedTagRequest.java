package bucheon.leafy.domain.feed.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
