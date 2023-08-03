package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedReplyService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.feed.request.FeedReplyRequest;
import bucheon.leafy.domain.feed.response.FeedReplyResponse;
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

@Tag(name = "피드 대댓글")
@RestController
@RequestMapping("/api/v1/feeds/{feedId}/comments/{commentId}/replies")
@RequiredArgsConstructor
public class FeedReplyController {

    private final FeedReplyService service;

    @Operation(summary = "피드 대댓글 리스트")
    @GetMapping
    public ResponseEntity<List<FeedReplyResponse>> getReplies(@PathVariable Long feedId, @PathVariable Long commentId, @RequestParam(required = false) ScrollRequest scrollRequest) {
        return ResponseEntity.ok().body(service.getReplies(feedId, commentId, scrollRequest));
    }

    @Operation(summary = "피드 대댓글 등록")
    @PostMapping
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<Long> saveReply(@AuthenticationPrincipal AuthUser user, @PathVariable Long feedId, @PathVariable Long commentId, @RequestBody FeedReplyRequest request) {
        Long userId = user.getUserId();
        return ResponseEntity.ok().body(service.saveReply(userId, feedId, commentId, request));
    }

    @Operation(summary = "피드 대댓글 수정")
    @PutMapping("/{replyId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<Map<String, Object>> updateReply(@AuthenticationPrincipal AuthUser user, @PathVariable Long feedId, @PathVariable Long commentId, @PathVariable Long replyId, @RequestBody FeedReplyRequest request) {
        Long userId = user.getUserId();
        return ResponseEntity.ok().body(service.updateReply(userId, feedId, commentId, replyId, request));
    }

    @Operation(summary = "피드 대댓글 삭제")
    @DeleteMapping("/{replyId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity deleteReply(@AuthenticationPrincipal AuthUser user, @PathVariable Long feedId, @PathVariable Long commentId, @PathVariable Long replyId) {
        Long userId = user.getUserId();
        return ResponseEntity.ok().body(service.deleteReply(userId, feedId, commentId, replyId));
    }

}
