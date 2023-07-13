package bucheon.leafy.application.controller;

import bucheon.leafy.application.response.MyPageResponse;
import bucheon.leafy.application.service.FeedLikeInfoService;
import bucheon.leafy.application.service.FeedService;
import bucheon.leafy.application.service.FollowService;
import bucheon.leafy.application.service.UserService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.feed.response.FeedMonthlyResponse;
import bucheon.leafy.domain.feed.response.FeedWithLikeCountResponse;
import bucheon.leafy.domain.follow.response.FollowersResponse;
import bucheon.leafy.domain.user.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "회원정보")
@RestController
@RequestMapping("/api/v1/users/my-page")
@RequiredArgsConstructor
public class MyPageController {

    private final FollowService followService;

    private final FeedService feedService;

    private final UserService userService;

    private final FeedLikeInfoService feedLikeInfoService;

    @Operation(summary = "마이페이지")
    @GetMapping
    public ResponseEntity<MyPageResponse> signOut(@AuthenticationPrincipal AuthUser authUser,
                                  @PageableDefault(page = 1, size = 6) Pageable pageable) {

        Long userId = authUser.getUserId();
        UserResponse userResponse = userService.getUserResponseByUserId(userId);
        Long followerCount = followService.getFollowerCount(userId);
        Long followingCount = followService.getFollowingCount(userId);
        List<FeedMonthlyResponse> feedMonthlyResponses = feedService.getCountGroupByMonthly(userId);
        List<FollowersResponse> followers = followService.getFollowers(userId, pageable).getContent();
        List<FollowersResponse> followings = followService.getFollowings(userId, pageable).getContent();
        List<FeedWithLikeCountResponse> likedFeeds = feedLikeInfoService.getFeedByUserId(userId, pageable);

        MyPageResponse myPageResponse = MyPageResponse.builder()
                .userResponse(userResponse)
                .followerCount(followerCount)
                .followingCount(followingCount)
                .feedMonthlyActivity(feedMonthlyResponses)
                .followers(followers)
                .followings(followings)
                .likedFeeds(likedFeeds)
                .build();

        return ResponseEntity.ok().body(myPageResponse);
    }

}
