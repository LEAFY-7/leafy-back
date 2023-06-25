package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedService;
import bucheon.leafy.domain.feed.dto.request.FeedRequest;
import bucheon.leafy.domain.feed.dto.response.FeedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "피드")
@RestController
@RequestMapping("/v1/feeds")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService service;

    @Operation(summary = "피드 리스트")
    @GetMapping
    public ResponseEntity<List<FeedResponse>> getFeeds(@RequestParam(required = false) Long lastFeedId) {
        List<FeedResponse> responseList;

        if( lastFeedId == null) {
            responseList = service.getFeeds();
        } else {
            responseList = service.getFeeds(lastFeedId);
        }
        return ResponseEntity.ok().body(responseList);
    }

    @Operation(summary = "피드 상세")
    @GetMapping("/feedId")
    public ResponseEntity<FeedResponse> getFeedById(@PathVariable Long feedId) {
        return ResponseEntity.ok().body(service.getFeedById(feedId));
    }

    @Operation(summary = "피드 등록")
    @PostMapping
    public ResponseEntity<Long> saveFeed(@RequestBody FeedRequest request) {
        return ResponseEntity.ok().body(service.saveFeed(request));
    }

    @Operation(summary = "피드 수정")
    @PutMapping("/feedId")
    public ResponseEntity<Long> updateFeed(@PathVariable Long feedId, @RequestBody FeedRequest request) {
        return ResponseEntity.ok().body(service.updateFeed(feedId, request));
    }

    @Operation(summary = "피드 삭제")
    @DeleteMapping("/feedId")
    public ResponseEntity<Boolean> deleteFeed(@PathVariable Long feedId) {
        return ResponseEntity.ok().body(service.deleteFeed(feedId));
    }
}
