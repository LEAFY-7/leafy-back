package bucheon.leafy.domain.feed.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FeedCommentResponse {
    private Long feedCommentId;
    private Long userId;
    private Long feedId;
    private String comment;
    private Date createdAt;
    private Date modifiedAt;

    @Builder
    public FeedCommentResponse(Long feedCommentId, Long userId, Long feedId, String comment, Date createdAt, Date modifiedAt) {
        this.feedCommentId = feedCommentId;
        this.userId = userId;
        this.feedId = feedId;
        this.comment = comment;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
