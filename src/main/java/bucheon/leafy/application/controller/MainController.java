package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.feed.response.PopularTagResponse;
import bucheon.leafy.util.request.ScrollRequest;
import bucheon.leafy.util.response.ScrollResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "메인 페이지")
@RestController
@RequestMapping("/api/v1/main")
@RequiredArgsConstructor
public class MainController {

    private final FeedService feedService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 코드 문제")
    })
    @Operation(summary = "메인 페이지 불러오기")
    @GetMapping
    public ResponseEntity getMain(@AuthenticationPrincipal AuthUser authUser) {
        Long userId = authUser.getUserId();
        List<PopularTagResponse> popularTags = feedService.getPopularTags();

        ScrollRequest scrollRequest = new ScrollRequest(0L);
        ScrollResponse scrollResponse;

        if (userId == null) {
            scrollResponse = feedService.getMainFeedsWhenNotLogin(scrollRequest);
        } else {
            scrollResponse = feedService.getMainFeedsWhenLogin(userId, scrollRequest);
        }

        return ResponseEntity.ok().body(popularTags);
    }

}
