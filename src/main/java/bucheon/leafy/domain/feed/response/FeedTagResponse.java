package bucheon.leafy.domain.feed.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedTagResponse {
    private Long feedTagId;
    private String tag;

    @Builder
    private FeedTagResponse(Long feedTagId, String tag) {
        this.feedTagId = feedTagId;
        this.tag = tag;
    }
}
