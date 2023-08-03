package bucheon.leafy.domain.feed.request;

import bucheon.leafy.domain.feed.FeedType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
public class FeedRequest {

    @JsonIgnore
    private Long feedId;
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
