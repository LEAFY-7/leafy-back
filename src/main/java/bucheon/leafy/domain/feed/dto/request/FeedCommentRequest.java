package bucheon.leafy.domain.feed.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FeedCommentRequest {
    private Long comment_id;
    private Long userId;
    private Long feed_id;
    private String comment;

    public FeedCommentRequest() {
    }

    @Builder
    private FeedCommentRequest(Long comment_id, Long userId, Long feed_id, String comment) {
        this.comment_id = comment_id;
        this.userId = userId;
        this.feed_id = feed_id;
        this.comment = comment;
    }
}
