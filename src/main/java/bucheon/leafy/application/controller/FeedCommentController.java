package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedCommentService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.feed.request.FeedCommentRequest;
import bucheon.leafy.domain.feed.response.FeedCommentResponse;
import bucheon.leafy.util.request.ScrollRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "피드 댓글")
@RestController
@RequestMapping("/api/v1/feeds/{feedId}/comments")
@RequiredArgsConstructor
public class FeedCommentController {

    private final FeedCommentService service;

    @Operation(summary = "피드 댓글 리스트")
    @GetMapping
    public ResponseEntity<List<FeedCommentResponse>> getComments(@PathVariable Long feedId, ScrollRequest scrollRequest) {
        return ResponseEntity.ok().body(service.getComments(feedId, scrollRequest));
    }

    @Operation(summary = "피드 댓글 등록")
    @PostMapping
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<Long> saveComment(@AuthenticationPrincipal AuthUser user, @PathVariable Long feedId, @RequestBody FeedCommentRequest request) {
        Long userId = user.getUserId();
        return ResponseEntity.ok().body(service.saveComment(userId, feedId, request));
    }

    @Operation(summary = "피드 댓글 수정")
    @PutMapping("/{commentId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<Map<String, Object>> updateComment(@AuthenticationPrincipal AuthUser user, @PathVariable Long feedId, @PathVariable Long commentId, @RequestBody FeedCommentRequest request) {
        Long userId = user.getUserId();
        return ResponseEntity.ok().body(service.updateComment(userId, feedId, commentId, request));
    }

    @Operation(summary = "피드 댓글 삭제")
    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<String> deleteComment(@AuthenticationPrincipal AuthUser user, @PathVariable Long feedId, @PathVariable Long commentId) {
        Long userId = user.getUserId();
        return ResponseEntity.ok().body(service.deleteComment(userId, feedId, commentId));
    }
}
