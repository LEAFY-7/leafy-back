package bucheon.leafy.domain.feed.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FeedReplyResponse {
    private Long replyId;
    private Long userId;
    private Long feedId;
    private Long commentId;
    private String reply;
    private Date createdAt;
    private Date modifiedAt;

    @Builder
    public FeedReplyResponse(Long replyId, Long userId, Long feedId, Long commentId, String reply, Date createdAt, Date modifiedAt) {
        this.replyId = replyId;
        this.userId = userId;
        this.feedId = feedId;
        this.commentId = commentId;
        this.reply = reply;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
