package bucheon.leafy.domain.feed.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FeedReplyResponse {
    private Long feedReplyId;
    private Long userId;
    private Long feedId;
    private Long commentId;
    private String reply;
    private Date createdAt;
    private Date modifiedAt;

    @Builder
    public FeedReplyResponse(Long feedReplyId, Long userId, Long feedId, Long commentId, String reply, Date createdAt, Date modifiedAt) {
        this.feedReplyId = feedReplyId;
        this.userId = userId;
        this.feedId = feedId;
        this.commentId = commentId;
        this.reply = reply;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
