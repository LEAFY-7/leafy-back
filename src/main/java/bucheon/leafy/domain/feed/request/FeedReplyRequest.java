package bucheon.leafy.domain.feed.request;

import lombok.Builder;
import lombok.Data;

@Data
public class FeedReplyRequest {
    private String reply;

    @Builder
    private FeedReplyRequest(String reply) {
        this.reply = reply;
    }
}