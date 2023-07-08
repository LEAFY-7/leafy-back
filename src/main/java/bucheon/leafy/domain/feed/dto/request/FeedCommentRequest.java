package bucheon.leafy.domain.feed.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FeedCommentRequest {
    private Long commentId;
    private Long userId;
    private Long feedId;
    private String comment;

    @Builder
    private FeedCommentRequest(Long commentId, Long userId, Long feedId, String comment) {
        this.commentId = commentId;
        this.userId = userId;
        this.feedId = feedId;
        this.comment = comment;
    }
}
