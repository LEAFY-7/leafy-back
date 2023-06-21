package bucheon.leafy.domain.feed.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class FeedReplyResponse {
    private Long reply_id;
    private Long user_id;
    private Long feed_id;
    private Long comment_id;
    private String reply;
    private Boolean is_delete;
    private Date modified_at;

    @Builder
    private FeedReplyResponse(Long reply_id, Long user_id, Long feed_id, Long comment_id, String reply, Boolean is_delete, Date modified_at) {
        this.reply_id = reply_id;
        this.user_id = user_id;
        this.feed_id = feed_id;
        this.comment_id = comment_id;
        this.reply = reply;
        this.is_delete = is_delete;
        this.modified_at = modified_at;
    }
}
