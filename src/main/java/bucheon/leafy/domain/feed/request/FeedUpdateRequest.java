package bucheon.leafy.domain.feed.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FeedUpdateRequest {
    private FeedRequest feedRequest;
    private List<FeedTagRequest> tagRequestList;
}
