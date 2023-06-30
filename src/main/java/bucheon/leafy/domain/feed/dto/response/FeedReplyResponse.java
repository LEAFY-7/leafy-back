package bucheon.leafy.domain.feed.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FeedReplyResponse {
    private Long reply_id;
    private Long userId;
    private Long feed_id;
    private Long comment_id;
    private String reply;
    private Boolean isDelete;
    private Date modified_at;

    @Builder
    private FeedReplyResponse(Long reply_id, Long userId, Long feed_id, Long comment_id, String reply, Boolean isDelete, Date modified_at) {
        this.reply_id = reply_id;
        this.userId = userId;
        this.feed_id = feed_id;
        this.comment_id = comment_id;
        this.reply = reply;
        this.isDelete = isDelete;
        this.modified_at = modified_at;
    }
}
