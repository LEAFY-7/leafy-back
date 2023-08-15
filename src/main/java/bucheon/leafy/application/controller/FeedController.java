package bucheon.leafy.application.controller;

import bucheon.leafy.application.controller.response.FeedByIdResponse;
import bucheon.leafy.application.service.FeedService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.util.request.ScrollRequest;
import bucheon.leafy.util.response.ScrollResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "피드")
@RestController
@RequestMapping("/api/v1/feeds")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService service;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "피드 리스트 조회 성공"),
            @ApiResponse(responseCode = "500", description = "피드 리스트 조회 실패")
    })
    @Operation(summary = "피드 리스트")
    @GetMapping
    public ResponseEntity<ScrollResponse> getFeeds(ScrollRequest scrollRequest) {
        return ResponseEntity.ok().body(service.getFeeds(scrollRequest));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "피드 상세 조회 성공"),
            @ApiResponse(responseCode = "500", description = "피드 상세 조회 실패")
    })
    @Operation(summary = "피드 상세")
    @GetMapping("/{feedId}")
    public ResponseEntity<FeedByIdResponse> getFeedById(@PathVariable Long feedId) {
        return ResponseEntity.ok().body(service.getFeedById(feedId));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "피드 등록 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "403", description = "권한이 없음"),
            @ApiResponse(responseCode = "500", description = "피드 등록 실패")
    })
    @Operation(summary = "피드 등록")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveFeed(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                         @ModelAttribute FeedRequest request) throws IOException {
        Long userId = user.getUserId();
        service.saveFeed(userId, request);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "피드 수정 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "403", description = "권한이 없음"),
            @ApiResponse(responseCode = "500", description = "피드 수정 실패")
    })
    @Operation(summary = "피드 수정")
    @PutMapping(value = "/{feedId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void updateFeed(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                             @PathVariable Long feedId,
                                             @ModelAttribute FeedRequest request) throws IOException {
        Long userId = user.getUserId();
        service.updateFeed(feedId, userId, request);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "피드 삭제 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "403", description = "권한이 없음"),
            @ApiResponse(responseCode = "500", description = "피드 삭제 실패")
    })
    @Operation(summary = "피드 삭제")
    @DeleteMapping("/{feedId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeed(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user, @PathVariable Long feedId) {
        Long userId = user.getUserId();
        service.deleteFeed(feedId, userId);
    }
}
