package bucheon.leafy.domain.feed.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedTagResponse {
    private Long tagId;
    private String tag;

    @Builder
    private FeedTagResponse(Long tagId, String tag) {
        this.tagId = tagId;
        this.tag = tag;
    }
}
