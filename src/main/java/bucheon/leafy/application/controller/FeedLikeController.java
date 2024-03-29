package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.AlarmService;
import bucheon.leafy.application.service.FeedLikeService;
import bucheon.leafy.config.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "피드 좋아요")
@RestController
@RequestMapping("/api/v1/feeds/{feedId}/like")
@RequiredArgsConstructor
public class FeedLikeController {

    private final FeedLikeService feedLikeService;
    private final AlarmService alarmService;

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "좋아요 등록 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "403", description = "권한이 없음")
    })
    @PostMapping
    @Operation(summary = "좋아요 등록")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void like(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                     @PathVariable Long feedId) {
        Long userId = user.getUserId();
        feedLikeService.saveLike(userId, feedId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "좋아요 삭제 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "403", description = "권한이 없음")
    })
    @DeleteMapping
    @Operation(summary = "좋아요 삭제")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void deleteLike(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                           @PathVariable Long feedId) {
        Long userId = user.getUserId();
        feedLikeService.deleteLike(userId, feedId);
    }

}
