package bucheon.leafy.domain.feed.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedCommentRequest {

    @JsonIgnore
    private Long commentId;
    private String comment;

    @Builder
    private FeedCommentRequest(String comment) {
        this.comment = comment;
    }
}
