package bucheon.leafy.domain.feed.request;

import bucheon.leafy.domain.feed.FeedType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedRequest {

    private Long id;
    private String title;

    private String content;

    private Boolean isHide;

    private Boolean isDelete;

    private FeedType feedType;

    @Builder
    private FeedRequest(Long id, String title, String content,
                        Boolean isHide, Boolean isDelete, FeedType feedType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isHide = isHide;
        this.isDelete = isDelete;
        this.feedType = feedType;
    }

}
