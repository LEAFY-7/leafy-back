package bucheon.leafy.application.controller.response;

import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.domain.feed.response.FeedTagResponse;
import bucheon.leafy.domain.user.response.FeedAuthorResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FeedByIdResponse {
    private FeedResponse feedResponse;
    private List<FeedTagResponse> tagResponses;
    private FeedAuthorResponse feedAuthorResponse;

    @Builder
    public FeedByIdResponse(FeedResponse feedResponse, List<FeedTagResponse> tagResponses,
                            FeedAuthorResponse feedAuthorResponse) {

        this.feedResponse = feedResponse;
        this.tagResponses = tagResponses;
        this.feedAuthorResponse = feedAuthorResponse;
    }
}
