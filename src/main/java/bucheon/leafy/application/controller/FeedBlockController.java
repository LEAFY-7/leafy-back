package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedBlockService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.feed.response.FeedReplyResponse;
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
import java.util.Map;

@Tag(name = "피드 차단")
@RestController
@RequestMapping("/api/v1/feeds/block")
@RequiredArgsConstructor
public class FeedBlockController {

    private final FeedBlockService feedBlockService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "피드 차단 목록 조회"),
            @ApiResponse(responseCode = "404", description = "피드가 삭제됨")
    })
    @Operation(summary = "피드 차단 목록")
    @GetMapping
    public ResponseEntity getBlockedFeeds(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                          @PageableDefault(page = 1, size = 20) Pageable pageable) {

        Long userId = authUser.getUserId();
        feedBlockService.getBlockedFeeds(userId, pageable);
        return ResponseEntity.ok().body(null);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "피드 차단 성공"),
            @ApiResponse(responseCode = "404", description = "피드가 삭제됨")
    })
    @Operation(summary = "피드 차단")
    @PostMapping("/{feedId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void blockFeed(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                          @PathVariable Long feedId) {
        Long userId = authUser.getUserId();
        feedBlockService.blockFeed(userId, feedId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "피드 차단 해제"),
            @ApiResponse(responseCode = "404", description = "피드가 삭제됨")
    })
    @Operation(summary = "피드 차단 해제")
    @DeleteMapping("/{feedId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void noneBlockFeed(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                      @PathVariable Long feedId) {
        Long userId = authUser.getUserId();
        feedBlockService.noneBlockFeed(userId, feedId);
    }


}
