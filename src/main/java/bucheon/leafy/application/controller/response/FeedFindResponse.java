package bucheon.leafy.application.controller.response;

import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.feed.request.FeedTagRequest;
import bucheon.leafy.domain.feed.response.FeedImageResponse;
import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.domain.feed.response.FeedTagResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FeedFindResponse {
    private FeedResponse feedResponse;
    private List<FeedTagResponse> tagResponseList;
    private List<FeedImageResponse> feedImageResponseList;

    @Builder
    public FeedFindResponse(FeedResponse feedResponse, List<FeedTagResponse> tagResponseList, List<FeedImageResponse> feedImageResponseList) {
        this.feedResponse = feedResponse;
        this.tagResponseList = tagResponseList;
        this.feedImageResponseList = feedImageResponseList;
    }
}
