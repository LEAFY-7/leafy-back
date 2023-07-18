package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FollowService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.follow.response.FollowersResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "팔로우")
@RestController
@RequestMapping("/api/v1/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @Operation(summary = "나를 팔로우한 회원들")
    @GetMapping("/followers")
    public ResponseEntity<Page<FollowersResponse>> getFollowers(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                                                @PageableDefault(page = 1, size = 20) Pageable pageable) {
        Long userId = authUser.getUserId();
        Page<FollowersResponse> result = followService.getFollowers(userId, pageable);
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "내가 팔로우한 회원들")
    @GetMapping("/followings")
    public ResponseEntity<Page<FollowersResponse>> getFollowings(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                                                 @PageableDefault(page = 1, size = 20) Pageable pageable) {
        Long userId = authUser.getUserId();
        Page<FollowersResponse> result = followService.getFollowings(userId, pageable);
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "팔로우")
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void follow(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                         @PathVariable("id") Long targetUserId) {
        Long userId = authUser.getUserId();
        followService.follow(userId, targetUserId);
    }

    @Operation(summary = "언팔로우")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unfollow(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                           @PathVariable("id") Long targetUserId) {
        Long userId = authUser.getUserId();
        followService.unfollow(userId, targetUserId);
    }

}
