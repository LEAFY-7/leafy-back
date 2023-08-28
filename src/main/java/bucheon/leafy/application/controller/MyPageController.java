package bucheon.leafy.application.controller;

import bucheon.leafy.application.controller.response.MyPageResponse;
import bucheon.leafy.application.service.*;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.feed.response.FeedMonthlyResponse;
import bucheon.leafy.domain.feed.response.FeedWithLikeCountResponse;
import bucheon.leafy.domain.follow.response.FollowersResponse;
import bucheon.leafy.domain.qna.response.MyPageQnaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final QnaService qnaService;

    private final FeedLikeInfoService feedLikeInfoService;
    private final UserBlockService userBlockService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "서버 코드 문제")
    })
    @Operation(summary = "마이페이지")
    @GetMapping
    public ResponseEntity<MyPageResponse> myPage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                  @PageableDefault(page = 0, size = 6) Pageable pageable) {

        Long userId = authUser.getUserId();
        Long followerCount = followService.getFollowerCount(userId);
        Long followingCount = followService.getFollowingCount(userId);
        List<FeedMonthlyResponse> feedMonthlyResponses = feedService.getCountGroupByMonthly(userId);
        List<FollowersResponse> followers = followService.getFollowers(userId, pageable).getBody();
        List<FollowersResponse> followings = followService.getFollowings(userId, pageable).getBody();
        List<FeedWithLikeCountResponse> feeds = feedLikeInfoService.getFeedByUserId(userId, pageable);
        List<MyPageQnaResponse> qnas = qnaService.getQnasByUserId(userId);

        MyPageResponse myPageResponse = MyPageResponse.builder()
                .followerCount(followerCount)
                .followingCount(followingCount)
                .feedMonthlyActivity(feedMonthlyResponses)
                .followers(followers)
                .followings(followings)
                .feeds(feeds)
                .qnas(qnas)
                .build();

        return ResponseEntity.ok().body(myPageResponse);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "404", description = "상대 회원이 존재하지 않음"),
            @ApiResponse(responseCode = "422", description = "상대 회원이 차단되거나 비공개 상태"),
            @ApiResponse(responseCode = "500", description = "서버 코드 문제")
    })
    @Operation(summary = "상대 페이지")
    @GetMapping("/{targetUserId}")
    public ResponseEntity<MyPageResponse> yourPage(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                                   @PathVariable Long targetUserId,
                                                   @PageableDefault(page = 0, size = 8) Pageable pageable) {
        Long userId = authUser.getUserId();
        userBlockService.isUserBlockedOrPrivate(userId, targetUserId);
        Long followerCount = followService.getFollowerCount(targetUserId);
        Long followingCount = followService.getFollowingCount(targetUserId);
        List<FeedMonthlyResponse> feedMonthlyResponses = feedService.getCountGroupByMonthly(targetUserId);
        List<FollowersResponse> followers = followService.getFollowers(targetUserId, pageable).getBody();
        List<FollowersResponse> followings = followService.getFollowings(targetUserId, pageable).getBody();
        List<FeedWithLikeCountResponse> feeds = feedLikeInfoService.getFeedByUserId(targetUserId, pageable);

        MyPageResponse myPageResponse = MyPageResponse.builder()
                .followerCount(followerCount)
                .followingCount(followingCount)
                .feedMonthlyActivity(feedMonthlyResponses)
                .followers(followers)
                .followings(followings)
                .feeds(feeds)
                .build();

        return ResponseEntity.ok().body(myPageResponse);
    }

}
