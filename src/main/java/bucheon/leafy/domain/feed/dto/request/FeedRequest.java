package bucheon.leafy.domain.feed.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FeedRequest {
    private Long feed_id;
    private Long userId;
    private String title;
    private String content;
    private Boolean is_hide;

    @Builder
    private FeedRequest(Long feed_id, Long userId, String title, String content, Boolean is_hide) {
        this.feed_id = feed_id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.is_hide = is_hide;
    }
}
