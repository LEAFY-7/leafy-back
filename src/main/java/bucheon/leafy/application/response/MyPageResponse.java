package bucheon.leafy.application.response;

import bucheon.leafy.domain.feed.response.FeedMonthlyResponse;
import bucheon.leafy.domain.feed.response.FeedWithLikeCountResponse;
import bucheon.leafy.domain.follow.response.FollowersResponse;
import bucheon.leafy.domain.user.response.UserResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class MyPageResponse {

    private UserResponse userResponse;

    private Long followerCount;

    private Long followingCount;

    private List<FeedMonthlyResponse> feedMonthlyActivity;

    private List<FollowersResponse> followers;

    private List<FollowersResponse> followings;

    private List<FeedWithLikeCountResponse> likedFeeds;

    @Builder
    private MyPageResponse(UserResponse userResponse, Long followerCount, Long followingCount,
                          List<FeedMonthlyResponse> feedMonthlyActivity, List<FollowersResponse> followers,
                          List<FollowersResponse> followings, List<FeedWithLikeCountResponse> likedFeeds) {

        this.userResponse = userResponse;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.feedMonthlyActivity = feedMonthlyActivity;
        this.followers = followers;
        this.followings = followings;
        this.likedFeeds = likedFeeds;
    }

    public static MyPageResponse of(UserResponse userResponse, Long followerCount, Long followingCount,
                                    List<FeedMonthlyResponse> feedMonthlyActivity, List<FollowersResponse> followers,
                                    List<FollowersResponse> followings, List<FeedWithLikeCountResponse> likedFeeds) {

        return MyPageResponse.builder()
                .userResponse(userResponse)
                .followerCount(followerCount)
                .followingCount(followingCount)
                .feedMonthlyActivity(feedMonthlyActivity)
                .followers(followers)
                .followings(followings)
                .likedFeeds(likedFeeds)
                .build();

    }

}
