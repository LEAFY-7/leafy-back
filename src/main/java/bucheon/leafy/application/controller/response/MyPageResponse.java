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

    private TotalCountResponse totalCountResponse;

    private List<FeedMonthlyResponse> feedMonthlyActivity;

    private List<FollowersResponse> followers;

    private List<FollowersResponse> followings;

    private List<FeedWithLikeCountResponse> feeds;
    private List<MyPageQnaResponse> qnas;

    @Builder
    private MyPageResponse(TotalCountResponse totalCountResponse,
                           List<FeedMonthlyResponse> feedMonthlyActivity,
                           List<FollowersResponse> followers,
                           List<FollowersResponse> followings,
                           List<FeedWithLikeCountResponse> feeds,
                           List<MyPageQnaResponse> qnas) {

        this.totalCountResponse = totalCountResponse;
        this.feedMonthlyActivity = feedMonthlyActivity;
        this.followers = followers;
        this.followings = followings;
        this.feeds = feeds;
        this.qnas = qnas;
    }

    public static MyPageResponse of(TotalCountResponse totalCountResponse,
                                    List<FeedMonthlyResponse> feedMonthlyActivity,
                                    List<FollowersResponse> followers,
                                    List<FollowersResponse> followings,
                                    List<FeedWithLikeCountResponse> feeds) {

        return MyPageResponse.builder()
                .totalCountResponse(totalCountResponse)
                .feedMonthlyActivity(feedMonthlyActivity)
                .followers(followers)
                .followings(followings)
                .feeds(feeds)
                .build();

    }

}
