package bucheon.leafy.application.controller.response;

import bucheon.leafy.domain.feed.response.FeedWithLikeCountResponse;
import bucheon.leafy.domain.user.response.UserResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class YourPageResponse {

    private UserResponse userResponse;

    private Long followerCount;

    private Long followingCount;

    private Integer feedCount;

    private List<FeedWithLikeCountResponse> feeds;

    @Builder
    private YourPageResponse(UserResponse userResponse, Long followerCount, Long followingCount,
                             Integer feedCount, List<FeedWithLikeCountResponse> feeds) {

        this.userResponse = userResponse;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.feedCount = feedCount;
        this.feeds = feeds;
    }

    public static YourPageResponse of(UserResponse userResponse, Long followerCount, Long followingCount,
                                       List<FeedWithLikeCountResponse> feeds) {

        return YourPageResponse.builder()
                .userResponse(userResponse)
                .followerCount(followerCount)
                .followingCount(followingCount)
                .feeds(feeds)
                .build();

    }

}
