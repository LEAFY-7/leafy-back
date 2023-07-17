package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedCommentService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.feed.request.FeedCommentRequest;
import bucheon.leafy.domain.feed.response.FeedCommentResponse;
import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.util.request.ScrollRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "피드 댓글")
@RestController
@RequestMapping("/api/v1/feeds/{feedId}/comments")
@RequiredArgsConstructor
public class FeedCommentController {

    private final FeedCommentService service;

    @Operation(summary = "피드 댓글 리스트")
    @GetMapping
    public ResponseEntity<List<FeedCommentResponse>> getComments(@RequestParam(required = false) ScrollRequest scrollRequest) {
        return ResponseEntity.ok().body(service.getComments(scrollRequest));
    }

    @Operation(summary = "피드 댓글 등록")
    @PostMapping
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<Long> saveComment(@PathVariable Long feedId, @RequestBody FeedCommentRequest request) {
        return ResponseEntity.ok().body(service.saveComment(feedId, request));
    }

    @Operation(summary = "피드 댓글 수정")
    @PutMapping("/{commentId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<String> updateComment(@AuthenticationPrincipal AuthUser user, @PathVariable Long feedId, @PathVariable Long commentId, @RequestBody FeedCommentRequest request) {
        Long userId = user.getUserId();
        FeedCommentResponse response = service.getCommentById(commentId);
        if( userId.equals(response.getUserId()) ) {
            return ResponseEntity.ok().body(service.updateComment(commentId, request));
        } else {
            throw new AccessDeniedException("수정 권한이 없습니다.");
        }
    }

    @Operation(summary = "피드 댓글 삭제")
    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    public ResponseEntity<String> deleteComment(@AuthenticationPrincipal AuthUser user, @PathVariable Long feedId, @PathVariable Long commentId) {
        Long userId = user.getUserId();
        FeedCommentResponse response = service.getCommentById(commentId);
        if( userId.equals(response.getUserId()) ) {
            return ResponseEntity.ok().body(service.deleteComment(commentId));
        } else {
            throw new AccessDeniedException("삭제 권한이 없습니다.");
        }
    }
}
