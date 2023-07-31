package bucheon.leafy.domain.feed.response;


import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class FeedResponse {

    private Long feedId;
    private Long userId;
    private String title;
    private String content;
    private FeedType feedType;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    private FeedResponse(Long feedId, Long userId, String title, String content,
                         FeedType feedType, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.feedId = feedId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.feedType = feedType;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static FeedResponse of(Feed feed) {
        return FeedResponse.builder()
                .feedId(feed.getId())
                .title(feed.getTitle())
                .content(feed.getContent())
                .feedType(feed.getFeedType())
                .createdAt(feed.getCreatedAt())
                .modifiedAt(feed.getModifiedAt())
                .build();
    }

}
