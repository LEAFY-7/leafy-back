package bucheon.leafy.application.controller.response;

import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.feed.request.FeedTagRequest;
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

    @Builder
    public FeedFindResponse(FeedResponse feedResponse, List<FeedTagResponse> tagResponseList) {
        this.feedResponse = feedResponse;
        this.tagResponseList = tagResponseList;
    }
}
