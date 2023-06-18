package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedService;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.request.FeedRequest;
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

    // 피드 리스트
    @GetMapping
    public ResponseEntity<List<Feed>> getFeedList(@RequestParam Long lastFeedId) {
        List<Feed> feedList;

        if( lastFeedId == null) {
            feedList = service.getFeeds();
        } else {
            feedList = service.getFeeds(lastFeedId);
        }
        return ResponseEntity.ok().body(feedList);
    }

    // 특정 피드
    @GetMapping("/{id}")
    public ResponseEntity<Feed> getFeedById(@PathVariable("id") Long id) {
        Feed feed = service.getFeedById(id);

        return ResponseEntity.ok().body(feed);
    }

    // 게시글 등록
    @PostMapping
    public ResponseEntity<Long> saveFeed(@RequestBody FeedRequest feed) {
        Long savedId = service.saveFeed(feed);

        return ResponseEntity.ok().body(savedId);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Long> updateFeed(@PathVariable("id") Long id,
                                           @RequestBody FeedRequest feed) {
        Long updatedId = service.updateFeed(feed);

        return ResponseEntity.ok().body(updatedId);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteFeed(@PathVariable("id") Long id) {
        Boolean isDelete =  service.deleteFeed(id);

        return ResponseEntity.ok().body(isDelete);
    }
}
