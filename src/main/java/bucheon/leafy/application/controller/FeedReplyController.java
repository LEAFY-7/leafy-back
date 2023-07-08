package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedReplyService;
import bucheon.leafy.domain.feed.dto.request.FeedReplyRequest;
import bucheon.leafy.domain.feed.dto.response.FeedReplyResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/feeds/{feedId}/comments/{commentId}/replies")
@RequiredArgsConstructor
public class FeedReplyController {

    private final FeedReplyService service;

    @Operation(summary = "피드 대댓글 리스트")
    @GetMapping
    public ResponseEntity<List<FeedReplyResponse>> getReplies(@PathVariable Long feedId, @PathVariable Long commentId) {

        return ResponseEntity.ok().body(service.getReplies(commentId));
    }

    @Operation(summary = "피드 대댓글 상세")
    @GetMapping("/{replyId}")
    public ResponseEntity<FeedReplyResponse> findReplyById(@PathVariable Long feedId, @PathVariable Long commentId, @PathVariable Long replyId) {
        return ResponseEntity.ok().body(service.findReplyById(replyId));
    }

    @Operation(summary = "피드 대댓글 등록")
    @PostMapping
    public ResponseEntity<Long> saveReply(@PathVariable Long commentId, @RequestBody FeedReplyRequest request) {
        return ResponseEntity.ok().body(service.saveReply(commentId, request));
    }

    @Operation(summary = "피드 대댓글 수정")
    @PutMapping("/{replyId}")
    public ResponseEntity<Long> updateReply(@PathVariable Long feedId, @PathVariable Long commentId, @PathVariable Long replyId, @RequestBody FeedReplyRequest request) {
        return ResponseEntity.ok().body(service.updateReply(feedId, commentId, replyId, request));
    }

    @Operation(summary = "피드 대댓글 삭제")
    @DeleteMapping("/{replyId}")
    public ResponseEntity deleteReply(@PathVariable Long feedId, @PathVariable Long commentId, @PathVariable Long replyId) {
        service.deleteReply(replyId);
        return ResponseEntity.ok().body("댓글 삭제 성공");
    }

}
