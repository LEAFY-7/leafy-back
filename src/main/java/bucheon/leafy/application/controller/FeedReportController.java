package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedReportService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.feed.response.FeedResponse;
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

@Tag(name = "피드 신고")
@RestController
@RequestMapping("/api/v1/feeds/report")
@RequiredArgsConstructor
public class FeedReportController {
    private final FeedReportService feedReportService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "피드 신고 목록 조회"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "404", description = "피드가 삭제됨")
    })
    @Operation(summary = "피드 신고 목록")
    @GetMapping
    public ResponseEntity getReportedFeeds(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                          @PageableDefault(page = 1, size = 20) Pageable pageable) {

        Long userId = authUser.getUserId();
        List<FeedResponse> reportedFeeds = feedReportService.getReportedFeeds(userId, pageable);
        return ResponseEntity.ok().body(reportedFeeds);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "피드 신고 성공"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "404", description = "피드가 삭제됨")
    })
    @Operation(summary = "피드 신고")
    @PostMapping("/{feedId}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void reportFeed(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                          @PathVariable Long feedId) {
        Long userId = authUser.getUserId();
        feedReportService.reportFeed(userId, feedId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "피드 신고 취소"),
            @ApiResponse(responseCode = "401, 403", description = "로그인 필요"),
            @ApiResponse(responseCode = "404", description = "피드가 삭제됨")
    })
    @Operation(summary = "피드 신고 취소")
    @DeleteMapping("/{feedId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public void reportCancelFeed(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                              @PathVariable Long feedId) {
        Long userId = authUser.getUserId();
        feedReportService.reportCancelFeed(userId, feedId);
    }
}
