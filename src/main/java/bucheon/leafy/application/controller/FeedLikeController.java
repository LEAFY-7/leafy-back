package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedLikeService;
import bucheon.leafy.config.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "피드 좋아요")
@RestController
@RequestMapping("/api/v1/feeds/{id}/like")
@RequiredArgsConstructor
public class FeedLikeController {

    private final FeedLikeService feedLikeService;

    @PostMapping
    @Operation(summary = "좋아요 등록")
    public ResponseEntity<String> like(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                       @PathVariable("id") Long feedId) {

        Long userId = user.getUserId();
        feedLikeService.saveLike(userId, feedId);
        return ResponseEntity.ok().body("성공적으로 실행되었습니다.");
    }

    @DeleteMapping
    @Operation(summary = "좋아요 삭제")
    public ResponseEntity<String> deleteLike(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                             @PathVariable("id") Long feedId) {

        Long userId = user.getUserId();
        feedLikeService.deleteLike(userId, feedId);
        return ResponseEntity.ok().body("성공적으로 실행되었습니다.");
    }

}
