package bucheon.leafy.domain.feed.response;

import bucheon.leafy.domain.feed.FeedType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FeedResponse {
    private Long feedId;
    private Long userId;
    private String title;
    private String content;
    private FeedType feedType;
    private Date createdAt;
    private Date modifiedAt;

    @Builder
    private FeedResponse(Long feedId, Long userId, String title, String content, FeedType feedType, Date createdAt, Date modifiedAt) {
        this.feedId = feedId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.feedType = feedType;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
