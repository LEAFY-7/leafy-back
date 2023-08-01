package bucheon.leafy.application.component.request;

import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.feed.request.FeedTagRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FeedUpdateRequest {
    private FeedRequest feedRequest;
    private List<FeedTagRequest> tagRequestList;
}
