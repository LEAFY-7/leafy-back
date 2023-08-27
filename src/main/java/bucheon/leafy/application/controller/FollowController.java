package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FollowService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.exception.SelfTargetException;
import bucheon.leafy.util.response.JpaPageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "팔로우")
@RestController
@RequestMapping("/api/v1/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "404", description = "나를 팔로우한 회원을 찾지 못함")
    })
    @Operation(summary = "나를 팔로우한 회원들")
    @GetMapping("/followers")
    public ResponseEntity<JpaPageResponse> getFollowers(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                                                @PageableDefault(page = 0, size = 20) Pageable pageable) {
        Long userId = authUser.getUserId();
        JpaPageResponse result = followService.getFollowers(userId, pageable);
        return ResponseEntity.ok().body(result);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "404", description = "내가 팔로우한 회원을 찾지 못함")
    })
    @Operation(summary = "내가 팔로우한 회원들")
    @GetMapping("/followings")
    public ResponseEntity<JpaPageResponse> getFollowings(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                                                 @PageableDefault(page = 0, size = 20) Pageable pageable) {
        Long userId = authUser.getUserId();
        JpaPageResponse result = followService.getFollowings(userId, pageable);
        return ResponseEntity.ok().body(result);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "팔로우 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "403", description = "권한이 없음"),
            @ApiResponse(responseCode = "404", description = "팔로우 할 회원을 찾지 못함"),
            @ApiResponse(responseCode = "409", description = "이미 팔로우함"),
            @ApiResponse(responseCode = "500", description = "자기 자신을 팔로우 했을 때 발생")
    })
    @Operation(summary = "팔로우")
    @PostMapping("/{targetUserId}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void follow(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                         @PathVariable Long targetUserId) {
        Long userId = authUser.getUserId();

        if (userId == targetUserId) {
            throw new SelfTargetException();
        }

        followService.follow(userId, targetUserId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "언팔로우 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "403", description = "권한이 없음"),
            @ApiResponse(responseCode = "404", description = "언팔로우 할 회원을 찾지 못함, 이미 언팔로우함"),
            @ApiResponse(responseCode = "500", description = "자기 자신을 팔로우 했을 때 발생")
    })
    @Operation(summary = "언팔로우")
    @DeleteMapping("/{targetUserId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void unfollow(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                           @PathVariable Long targetUserId) {
        Long userId = authUser.getUserId();

        if (userId == targetUserId) {
            throw new SelfTargetException();
        }

        followService.unfollow(userId, targetUserId);
    }

}
