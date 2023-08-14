package bucheon.leafy.application.controller;

import bucheon.leafy.application.controller.request.FeedSaveRequest;
import bucheon.leafy.application.controller.request.FeedUpdateRequest;
import bucheon.leafy.application.controller.response.FeedFindResponse;
import bucheon.leafy.application.service.FeedService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.util.request.ScrollRequest;
import bucheon.leafy.util.response.ScrollResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    public ResponseEntity<FeedFindResponse> getFeedById(@PathVariable Long feedId) {
        return ResponseEntity.ok().body(service.getFeedById(feedId));
    }

    @Operation(summary = "피드 등록")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> saveFeed(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                         @ModelAttribute FeedSaveRequest feedRequest) throws IOException {
        Long userId = user.getUserId();
        return ResponseEntity.ok().body(service.saveFeed(userId, feedRequest));
    }

    @Operation(summary = "피드 수정")
    @PutMapping(value = "/{feedId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<String> updateFeed(@AuthenticationPrincipal AuthUser user,
                                             @PathVariable Long feedId,
                                             @ModelAttribute FeedUpdateRequest request,
                                             @RequestPart List<MultipartFile> fileList) throws IOException {
        Long userId = user.getUserId();
        return ResponseEntity.ok().body(service.updateFeed(feedId, userId, request, fileList));
    }

    @Operation(summary = "피드 삭제")
    @DeleteMapping("/{feedId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<String> deleteFeed(@AuthenticationPrincipal AuthUser user, @PathVariable Long feedId) {
        Long userId = user.getUserId();

        return ResponseEntity.ok().body(service.deleteFeed(feedId, userId));
    }
}
