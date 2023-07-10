package bucheon.leafy.domain.feed.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FeedCommentResponse {
    private Long comment_id;
    private Long user_id;
    private Long feed_id;
    private String comment;
    private Date modified_at;

    @Builder
    private FeedCommentResponse(Long comment_id, Long user_id, Long feed_id, String comment, Date modified_at) {
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.feed_id = feed_id;
        this.comment = comment;
        this.modified_at = modified_at;
    }
}
