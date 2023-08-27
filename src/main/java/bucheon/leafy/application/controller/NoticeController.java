package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.NoticeService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.notice.request.NoticeEditRequest;
import bucheon.leafy.domain.notice.request.NoticeSaveRequest;
import bucheon.leafy.domain.notice.response.NoticeEditResponse;
import bucheon.leafy.domain.notice.response.NoticeSaveResponse;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notice")
public class NoticeController {

    private final NoticeService noticeService;
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Notice 글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "Notice 글 수정 실패")
    })
    @Operation(summary = "Notice 게시판 글 수정하기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{noticeId}")
    public ResponseEntity<NoticeEditResponse> modify(@PathVariable("noticeId")  Long noticeId, @AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                                     @RequestBody NoticeEditRequest noticeEditRequest) {

        Long userId = user.getUserId();
        return ResponseEntity.ok().body(noticeService.modify(noticeId, noticeEditRequest));
    }
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Notice 글 작성 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "Notice 글 작성 실패")
    })
    @Operation(summary = "Notice 게시판에 글 작성하기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public ResponseEntity<NoticeSaveResponse> write(
                                                    @AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
                                                    @RequestBody NoticeSaveRequest noticeSaveRequest) {
        Long userId = authUser.getUserId();

        NoticeSaveResponse response = noticeService.write(noticeSaveRequest , userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Notice 글 읽기  성공"),
            @ApiResponse(responseCode = "500", description = "Notice 글 읽기 실패")
    })
    @Operation(summary = "Notice 게시판 클릭 글 읽기")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/{noticeId}")
    public ResponseEntity<Object> read( @PathVariable("noticeId")  Long noticeId) {
        return ResponseEntity.ok().body(noticeService.getRead(noticeId));
    }
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Notice 글 목록 보여주기 성공"),
            @ApiResponse(responseCode = "500", description = "Notice 글 목록 보여주기 실패")
    })
    @Operation(summary = "Notice 게시판의 전체 글 목록 보기")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping
    public ResponseEntity<PageResponse> list(@ModelAttribute PageRequest pageRequest) {
        return ResponseEntity.ok().body(noticeService.getList(pageRequest));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Notice 글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "Notice 글 삭제 실패")
    })
    @Operation(summary = "Notice 게시판 글 삭제하기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{noticeId}/remove") // 이 경로는 삭제를 나타냄
    public ResponseEntity<Object> remove(@PathVariable("noticeId") Long noticeId,
                                         @AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        return ResponseEntity.ok().body(noticeService.remove(noticeId));
    }

    @Operation(summary = "Notice 게시판 글 숨기기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{noticeId}/hide") // 이 경로는 숨기기를 나타냄
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void hide(@PathVariable("noticeId") Long noticeId,
                     @AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        noticeService.hideByNoticeId(noticeId);
    }
}
