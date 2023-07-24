package bucheon.leafy.domain.feed.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FeedTagRequest {

    private Long feedId;
    private Long tagId;
    private String tag;

    @Builder
    private FeedTagRequest(Long feedId, Long tagId, String tag) {
        this.feedId = feedId;
        this.tagId = tagId;
        this.tag = tag;
    }
}
