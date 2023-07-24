package bucheon.leafy.domain.feed.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedTagResponse {
    private Long tagId;
    private Long feedId;
    private String tag;

    @Builder
    private FeedTagResponse(Long tagId, Long feedId, String tag) {
        this.tagId = tagId;
        this.feedId = feedId;
        this.tag = tag;
    }
}
