package bucheon.leafy.domain.feed.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FeedCommentResponse {
    private Long comment_id;
    private Long userId;
    private Long feed_id;
    private String comment;
    private Boolean isDelete;
    private Date modified_at;


    @Builder
    private FeedCommentResponse(Long comment_id, Long userId, Long feed_id, String comment, Boolean isDelete, Date modified_at) {
        this.comment_id = comment_id;
        this.userId = userId;
        this.feed_id = feed_id;
        this.comment = comment;
        this.isDelete = isDelete;
        this.modified_at = modified_at;
    }
}
