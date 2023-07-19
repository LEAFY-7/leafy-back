package bucheon.leafy.domain.feed.request;

import lombok.Builder;
import lombok.Data;

@Data
public class FeedCommentRequest {
    private Long commentId;
    private Long userId;
    private Long feedId;
    private String comment;

    @Builder
    private FeedCommentRequest(String comment) {
        this.comment = comment;
    }
}
