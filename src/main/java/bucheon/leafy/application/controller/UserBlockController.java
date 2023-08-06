package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FollowService;
import bucheon.leafy.application.service.UserBlockService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.user.response.UserResponse;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "회원 차단")
@RestController
@RequestMapping("/api/v1/users/block")
@RequiredArgsConstructor
public class UserBlockController {

    private final UserBlockService userBlockService;
    private final FollowService followService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "차단한 회원 목록 조회"),
            @ApiResponse(responseCode = "404", description = "회원이 탈퇴함")
    })
    @Operation(summary = "회원 차단 목록")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getBlockedUsers(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                                              @PageableDefault(page = 1, size = 20) Pageable pageable) {

        Long userId = authUser.getUserId();
        List<UserResponse> blockedUsers = userBlockService.getBlockedUsers(userId, pageable);
        return ResponseEntity.ok().body(blockedUsers);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "차단되어 있지 않음"),
            @ApiResponse(responseCode = "404", description = "회원이 탈퇴함"),
            @ApiResponse(responseCode = "409", description = "차단되어 있음"),
            @ApiResponse(responseCode = "500", description = "자기 자신을 대상으로 할 수 없음")
    })
    @Operation(summary = "단일 회원 차단 여부")
    @GetMapping("/{blockUserId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void isBlockedUser(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                                  @PathVariable Long blockUserId) {

        Long userId = authUser.getUserId();
        userBlockService.isBlockedUser(userId, blockUserId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원 차단 성공"),
            @ApiResponse(responseCode = "404", description = "회원이 탈퇴함"),
            @ApiResponse(responseCode = "500", description = "자기 자신을 대상으로 할 수 없음")
    })
    @Operation(summary = "회원 차단")
    @PostMapping("/{blockUserId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void blockUser(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                           @PathVariable Long blockUserId) {

        Long userId = authUser.getUserId();
        userBlockService.blockUser(userId, blockUserId);
        followService.unfollow(userId, blockUserId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "회원 차단 해제 성공"),
            @ApiResponse(responseCode = "404", description = "회원이 탈퇴함"),
            @ApiResponse(responseCode = "500", description = "자기 자신을 대상으로 할 수 없음")
    })
    @Operation(summary = "회원 차단 해제")
    @DeleteMapping("/{blockUserId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void noneBlockUser(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                 @PathVariable Long blockUserId) {
        Long userId = authUser.getUserId();
        userBlockService.noneBlockUser(userId, blockUserId);
    }

}
