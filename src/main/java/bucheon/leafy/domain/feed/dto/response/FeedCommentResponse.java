package bucheon.leafy.domain.feed.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class FeedCommentResponse {
    private Long comment_id;
    private Long user_id;
    private Long feed_id;
    private String comment;
    private Boolean is_delete;
    private Date modified_at;

    public FeedCommentResponse() {

    }

    @Builder
    private FeedCommentResponse(Long comment_id, Long user_id, Long feed_id, String comment, Boolean is_delete, Date modified_at) {
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.feed_id = feed_id;
        this.comment = comment;
        this.is_delete = is_delete;
        this.modified_at = modified_at;
    }
}
