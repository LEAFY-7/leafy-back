package bucheon.leafy.domain.feed.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedReplyRequest {

    @JsonIgnore
    private Long replyId;
    private String reply;

    @Builder
    private FeedReplyRequest(String reply) {
        this.reply = reply;
    }
}