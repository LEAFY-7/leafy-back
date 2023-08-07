package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.UserReportService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.user.response.UserResponse;
import bucheon.leafy.exception.SelfTargetException;
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

import java.util.List;

@Tag(name = "회원 신고")
@RestController
@RequestMapping("/api/v1/users/report")
@RequiredArgsConstructor
public class UserReportController {
    private final UserReportService userReportService;

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "신고한 회원 목록 조회"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "404", description = "회원이 탈퇴함")
    })
    @Operation(summary = "회원 차단 목록")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getReportedUsers(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                                               @PageableDefault(page = 1, size = 20) Pageable pageable) {

        Long userId = authUser.getUserId();
        List<UserResponse> blockedUsers = userReportService.getReportedUsers(userId, pageable);
        return ResponseEntity.ok().body(blockedUsers);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원 신고 성공"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "404", description = "회원이 탈퇴함"),
            @ApiResponse(responseCode = "500", description = "자기 자신을 대상으로 할 수 없음")
    })
    @Operation(summary = "회원 신고")
    @PostMapping("/{reportUserId}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void reportUser(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                           @PathVariable Long reportUserId) {
        Long userId = authUser.getUserId();

        if (userId == reportUserId) {
            throw new SelfTargetException();
        }

        userReportService.reportUser(userId, reportUserId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "회원 신고 취소 성공"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "404", description = "회원이 탈퇴함"),
            @ApiResponse(responseCode = "500", description = "자기 자신을 대상으로 할 수 없음")
    })
    @Operation(summary = "회원 신고 취소")
    @DeleteMapping("/{reportUserId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void reportCancelUser(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                 @PathVariable Long reportUserId) {
        Long userId = authUser.getUserId();

        if (userId == reportUserId) {
            throw new SelfTargetException();
        }

        userReportService.reportCancelUser(userId, reportUserId);
    }
}
