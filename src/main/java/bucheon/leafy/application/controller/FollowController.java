 package bucheon.leafy.application.controller;

 import bucheon.leafy.application.service.FollowService;
 import bucheon.leafy.application.service.UserService;
 import bucheon.leafy.config.AuthUser;
 import bucheon.leafy.domain.follow.response.FollowersResponse;
 import bucheon.leafy.domain.user.User;
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

 @Tag(name = "팔로우")
@RestController
@RequestMapping("/v1/follows")
@RequiredArgsConstructor
public class FollowController {

    private final UserService userService;
    private final FollowService followService;


    @Operation(summary = "나를 팔로우한 회원들")
    @GetMapping("/followers")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity getFollowers(@AuthenticationPrincipal AuthUser authUser,
                                       @PageableDefault(page = 0, size = 20) Pageable pageable) {

        User user = authUser.getUser();
        List<FollowersResponse> result = followService.getFollowers(user, pageable);
        return ResponseEntity.status(200).body(result);
    }

    @Operation(summary = "내가 팔로우한 회원들")
    @GetMapping("/followings")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity getFollowings(@AuthenticationPrincipal AuthUser authUser,
                                       @PageableDefault(page = 0, size = 20) Pageable pageable) {

        User user = authUser.getUser();
        List<FollowersResponse> result = followService.getFollowings(user, pageable);
        return ResponseEntity.status(200).body(result);
    }

    @Operation(summary = "팔로우")
    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity follow(@AuthenticationPrincipal AuthUser authUser,
                                 @PathVariable("id") Long userId) {

        User user = authUser.getUser();
        return followService.follow(user, userId);
    }

    @Operation(summary = "언팔로우")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity unfollow(@AuthenticationPrincipal AuthUser authUser,
                                   @PathVariable("id") Long userId) {

        User user = authUser.getUser();
        return followService.unfollow(user, userId);
    }

}
