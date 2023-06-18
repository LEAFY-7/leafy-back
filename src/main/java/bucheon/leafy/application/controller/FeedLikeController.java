package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedLikeService;
import bucheon.leafy.application.service.FeedService;
import bucheon.leafy.application.service.UserInfoService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.feed.Feed;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "피드 좋아요")
@RestController
@RequestMapping("/v1/feeds/{id}/like")
@RequiredArgsConstructor
public class FeedLikeController {

    private final FeedService feedService;
    private final FeedLikeService feedLikeService;
    private final UserInfoService userLikeService;

    @PostMapping
    public ResponseEntity like(@AuthenticationPrincipal AuthUser user,
                                      @PathVariable("id") Long id) {
        Long userId = user.getUserId();

        Feed feed = feedService.getFeedById(id);
        feedLikeService.saveLike(feed);

        userLikeService.saveLikeInfo(userId, feed);
        return ResponseEntity.ok().body("성공적으로 실행되었습니다.");
    }

    @DeleteMapping
    public ResponseEntity deleteLike(@AuthenticationPrincipal AuthUser user,
                                      @PathVariable("id") Long id) {
        Long userId = user.getUserId();

        Feed feed = feedService.getFeedById(id);
        feedLikeService.deleteLike(feed);

        userLikeService.deleteLikeInfo(userId, feed);
        return ResponseEntity.ok().body("성공적으로 실행되었습니다.");
    }

}
