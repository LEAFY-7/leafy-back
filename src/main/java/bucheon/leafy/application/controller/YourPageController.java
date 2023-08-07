package bucheon.leafy.application.controller;

import bucheon.leafy.application.controller.response.YourPageResponse;
import bucheon.leafy.application.service.FeedLikeInfoService;
import bucheon.leafy.application.service.FeedService;
import bucheon.leafy.application.service.FollowService;
import bucheon.leafy.application.service.UserService;
import bucheon.leafy.domain.feed.response.FeedWithLikeCountResponse;
import bucheon.leafy.domain.user.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "상대방 회원정보")
@RestController
@RequestMapping("/api/v1/users/your-page")
@RequiredArgsConstructor
public class YourPageController {

    private final FollowService followService;

    private final FeedService feedService;

    private final UserService userService;

    private final FeedLikeInfoService feedLikeInfoService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "서버 코드 문제")
    })
    @Operation(summary = "상대 페이지")
    @GetMapping("/{userId}")
    public ResponseEntity<YourPageResponse> yourPage(@PathVariable Long userId,
                                  @PageableDefault(page = 1, size = 8) Pageable pageable) {

        UserResponse userResponse = userService.getUserResponseByUserId(userId);
        Long followerCount = followService.getFollowerCount(userId);
        Long followingCount = followService.getFollowingCount(userId);
        Integer feedCount = feedService.getCountByUserId(userId);
        List<FeedWithLikeCountResponse> feeds = feedLikeInfoService.getFeedByUserId(userId, pageable);

        YourPageResponse yourPageResponse = YourPageResponse.builder()
                .userResponse(userResponse)
                .followerCount(followerCount)
                .followingCount(followingCount)
                .feedCount(feedCount)
                .feeds(feeds)
                .build();

        return ResponseEntity.ok().body(yourPageResponse);
    }

}
