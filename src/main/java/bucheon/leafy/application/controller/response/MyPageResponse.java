package bucheon.leafy.application.controller.response;

import bucheon.leafy.domain.feed.response.FeedMonthlyResponse;
import bucheon.leafy.domain.feed.response.FeedWithLikeCountResponse;
import bucheon.leafy.domain.follow.response.FollowersResponse;
import bucheon.leafy.domain.qna.response.MyPageQnaResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class MyPageResponse {

    private Long feedCount;
    private Long followerCount;

    private Long followingCount;

    private List<FeedMonthlyResponse> feedMonthlyActivity;

    private List<FollowersResponse> followers;

    private List<FollowersResponse> followings;

    private List<FeedWithLikeCountResponse> feeds;
    private List<MyPageQnaResponse> qnas;

    @Builder
    private MyPageResponse(Long feedCount, Long followerCount, Long followingCount,
                           List<FeedMonthlyResponse> feedMonthlyActivity,
                           List<FollowersResponse> followers, List<FollowersResponse> followings,
                           List<FeedWithLikeCountResponse> feeds, List<MyPageQnaResponse> qnas) {

        this.feedCount = feedCount;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.feedMonthlyActivity = feedMonthlyActivity;
        this.followers = followers;
        this.followings = followings;
        this.feeds = feeds;
        this.qnas = qnas;
    }

    public static MyPageResponse of(Long feedCount, Long followerCount, Long followingCount,
                                    List<FeedMonthlyResponse> feedMonthlyActivity,
                                    List<FollowersResponse> followers, List<FollowersResponse> followings,
                                    List<FeedWithLikeCountResponse> feeds, List<MyPageQnaResponse> qnas) {

        return MyPageResponse.builder()
                .followerCount(feedCount)
                .followerCount(followerCount)
                .followingCount(followingCount)
                .feedMonthlyActivity(feedMonthlyActivity)
                .followers(followers)
                .followings(followings)
                .feeds(feeds)
                .build();

    }

}
