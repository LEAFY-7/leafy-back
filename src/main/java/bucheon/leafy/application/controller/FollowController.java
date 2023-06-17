 package bucheon.leafy.application.controller;

 import bucheon.leafy.application.service.FollowService;
 import bucheon.leafy.config.AuthUser;
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

@Tag(name = "팔로우")
@RestController
@RequestMapping("/v1/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @Operation(summary = "팔로워 리스트")
    @GetMapping
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity getFollowers(@AuthenticationPrincipal AuthUser authUser,
                                       @PageableDefault(page = 0, size = 20) Pageable pageable) {

        User user = authUser.getUser();
        return followService.getFollowers(user, pageable);
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
