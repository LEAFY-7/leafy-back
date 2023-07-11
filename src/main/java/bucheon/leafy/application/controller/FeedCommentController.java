package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedCommentService;
import bucheon.leafy.domain.feed.request.FeedCommentRequest;
import bucheon.leafy.domain.feed.response.FeedCommentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<FeedCommentResponse>> getComments(@PathVariable Long feedId) {
        return ResponseEntity.ok().body(service.getComments(feedId));
    }

    @Operation(summary = "피드 댓글 상세")
    @GetMapping("/{commentId}")
    public ResponseEntity<FeedCommentResponse> findCommentById(@PathVariable Long feedId, @PathVariable Long commentId) {
        return ResponseEntity.ok().body(service.findCommentById(commentId));
    }

    @Operation(summary = "피드 댓글 등록")
    @PostMapping
    public ResponseEntity<Long> saveComment(@PathVariable Long feedId, @RequestBody FeedCommentRequest request) {
        return ResponseEntity.ok().body(service.saveComment(feedId, request));
    }

    @Operation(summary = "피드 댓글 수정")
    @PutMapping("/{commentId}")
    public ResponseEntity<Long> updateComment(@PathVariable Long feedId, @PathVariable Long commentId, @RequestBody FeedCommentRequest request) {
        return ResponseEntity.ok().body(service.updateComment(feedId, commentId, request));
    }

    @Operation(summary = "피드 댓글 삭제")
    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long feedId, @PathVariable Long commentId) {
        service.deleteComment(commentId);
        return ResponseEntity.ok().body("댓글 삭제 성공");
    }
}
