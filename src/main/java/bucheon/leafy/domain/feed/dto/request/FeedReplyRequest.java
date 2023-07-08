package bucheon.leafy.domain.feed.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class FeedReplyRequest {
    private Long replyId;
    private Long userId;
    private Long feedId;
    private Long commentId;
    private String reply;

    @Builder
    private FeedReplyRequest(Long replyId, Long userId, Long feedId, Long commentId, String reply) {
        this.replyId = replyId;
        this.userId = userId;
        this.feedId = feedId;
        this.commentId = commentId;
        this.reply = reply;
    }
}