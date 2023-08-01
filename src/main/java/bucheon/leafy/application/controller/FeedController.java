package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.application.component.request.FeedUpdateRequest;
import bucheon.leafy.util.request.ScrollRequest;
import bucheon.leafy.util.response.ScrollResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "피드")
@RestController
@RequestMapping("/api/v1/feeds")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService service;

    @Operation(summary = "피드 리스트")
    @GetMapping
    public ResponseEntity<ScrollResponse> getFeeds(ScrollRequest scrollRequest) {
        return ResponseEntity.ok().body(service.getFeeds(scrollRequest));
    }

    @Operation(summary = "피드 상세")
    @GetMapping("/{feedId}")
    public ResponseEntity<Map<String, Object>> getFeedById(@PathVariable Long feedId) {
        return ResponseEntity.ok().body(service.getFeedById(feedId));
    }

    @Operation(summary = "피드 등록")
    @PostMapping
    public ResponseEntity<Long> saveFeed(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                         @RequestBody FeedRequest request, @RequestParam List<String> tagList) {
        Long userId = user.getUserId();
        return ResponseEntity.ok().body(service.saveFeed(userId, request, tagList));
    }

    @Operation(summary = "피드 수정")
    @PutMapping("/{feedId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<String> updateFeed(@AuthenticationPrincipal AuthUser user, @PathVariable Long feedId, @RequestBody FeedUpdateRequest request) {
        Long userId = user.getUserId();

        return ResponseEntity.ok().body(service.updateFeed(feedId, userId, request));
    }

    @Operation(summary = "피드 삭제")
    @DeleteMapping("/{feedId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<String> deleteFeed(@AuthenticationPrincipal AuthUser user, @PathVariable Long feedId) {
        Long userId = user.getUserId();

        return ResponseEntity.ok().body(service.deleteFeed(feedId, userId));
    }
}
