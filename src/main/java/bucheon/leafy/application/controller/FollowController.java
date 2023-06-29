package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FollowService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.follow.response.FollowersResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "팔로우!!")
@RestController
@RequestMapping("/v1/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @Operation(summary = "나를 팔로우한 회원들")
    @GetMapping("/followers")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<List<FollowersResponse>> getFollowers(@AuthenticationPrincipal AuthUser authUser,
                                                                @PageableDefault(page = 0, size = 20) Pageable pageable) {
        Long userId = authUser.getUserId();
        List<FollowersResponse> result = followService.getFollowers(userId, pageable);
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "내가 팔로우한 회원들")
    @GetMapping("/followings")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<List<FollowersResponse>> getFollowings(@AuthenticationPrincipal AuthUser authUser,
                                                                 @PageableDefault(page = 0, size = 20) Pageable pageable) {
        Long userId = authUser.getUserId();
        List<FollowersResponse> result = followService.getFollowings(userId, pageable);
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "팔로우")
    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<String> follow(@AuthenticationPrincipal AuthUser authUser,
                                         @PathVariable("id") Long targetUserId) {

        Long userId = authUser.getUserId();
        followService.follow(userId, targetUserId);

        return ResponseEntity.ok().body("팔로우 성공");
    }

    @Operation(summary = "언팔로우")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<String> unfollow(@AuthenticationPrincipal AuthUser authUser,
                                           @PathVariable("id") Long targetUserId) {
        Long userId = authUser.getUserId();
        followService.unfollow(userId, targetUserId);
        return ResponseEntity.ok().body("언팔로우 성공");
    }

}
