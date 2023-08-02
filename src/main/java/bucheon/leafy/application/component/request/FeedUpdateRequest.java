package bucheon.leafy.application.component.request;

import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.feed.request.FeedTagRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FeedUpdateRequest {
    private FeedRequest feedRequest;
    private List<FeedTagRequest> tagRequestList;

    @Builder
    public FeedUpdateRequest(FeedRequest feedRequest, List<FeedTagRequest> tagRequestList) {
        this.feedRequest = feedRequest;
        this.tagRequestList = tagRequestList;
    }
}
