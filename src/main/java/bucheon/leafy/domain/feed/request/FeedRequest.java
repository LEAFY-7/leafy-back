package bucheon.leafy.domain.feed.request;

import bucheon.leafy.domain.feed.FeedType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedRequest {
    private Long feedId;
    private Long userId;
    private String title;
    private String content;
    private FeedType feedType;

    @Builder
    private FeedRequest(String title, String content, FeedType feedType) {
        this.title = title;
        this.content = content;
        this.feedType = feedType;
    }
}
