package bucheon.leafy.domain.feed.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FeedCommentRequest {
    private Long comment_id;
    private Long user_id;
    private Long feed_id;
    private String comment;

    public FeedCommentRequest() {
    }

    @Builder
    private FeedCommentRequest(Long comment_id, Long user_id, Long feed_id, String comment) {
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.feed_id = feed_id;
        this.comment = comment;
    }
}
