package bucheon.leafy.domain.feed.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FeedRequest {
    private Long feed_id;
    private Long user_id;
    private String title;
    private String content;
    private Boolean is_hide;

    @Builder
    private FeedRequest(Long feed_id, Long user_id, String title, String content, Boolean is_hide) {
        this.feed_id = feed_id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.is_hide = is_hide;
    }
}
