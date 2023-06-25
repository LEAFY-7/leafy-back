package bucheon.leafy.domain.feed.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FeedReplyRequest {
    private Long user_id;
    private Long feed_id;
    private Long comment_id;
    private String reply;

    @Builder
    private FeedReplyRequest(Long user_id, Long feed_id, Long comment_id, String reply) {
        this.user_id = user_id;
        this.feed_id = feed_id;
        this.comment_id = comment_id;
        this.reply = reply;
    }
}
