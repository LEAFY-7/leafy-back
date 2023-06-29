package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedCommentService;
import bucheon.leafy.domain.feed.dto.request.FeedCommentRequest;
import bucheon.leafy.domain.feed.dto.response.FeedCommentResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "피드 댓글~~~~~~~~~~~~~~")
@RestController
@RequestMapping("/v1/feeds/{feedId}/comments")
@RequiredArgsConstructor
public class FeedCommentController {

    private final FeedCommentService service;

    @GetMapping
    public ResponseEntity<List<FeedCommentResponse>> getComments(@PathVariable Long feedId) {
        return ResponseEntity.ok().body(service.getComments(feedId));
    }

    @PostMapping
    public ResponseEntity<Long> saveComment(@PathVariable Long feedId, @RequestBody FeedCommentRequest request) {
        return ResponseEntity.ok().body(service.saveComment(feedId, request));
    }

    @PutMapping("/commentId")
    public ResponseEntity<Long> updateComment(@PathVariable Long feedId, @PathVariable Long commentId, @RequestBody FeedCommentRequest request) {
        return ResponseEntity.ok().body(service.updateComment(feedId, commentId, request));
    }

    @DeleteMapping("/commentId")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long feedId, @PathVariable Long commentId) {
        return ResponseEntity.ok().body(service.deleteComment(feedId, commentId));
    }
}
