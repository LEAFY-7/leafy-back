package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.NoticeService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.notice.request.NoticeEditRequest;
import bucheon.leafy.domain.notice.request.NoticeSaveRequest;
import bucheon.leafy.domain.notice.response.NoticeEditResponse;
import bucheon.leafy.domain.notice.response.NoticeResponse;
import bucheon.leafy.domain.notice.response.NoticeSaveResponse;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "공지사항")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "공지사항 수정 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "403", description = "권한이 없음"),
            @ApiResponse(responseCode = "500", description = "공지사항 수정 실패")
    })
    @Operation(summary = "공지사항 수정")
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{noticeId}")
    public ResponseEntity<NoticeEditResponse> modify(@PathVariable("noticeId")  Long noticeId,
                                                     @AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                                     @RequestBody NoticeEditRequest noticeEditRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noticeService.modify(noticeId, noticeEditRequest, user));
    }


    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "공지사항 등록 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "403", description = "권한이 없음"),
            @ApiResponse(responseCode = "500", description = "공지사항 등록 실패")
    })
    @Operation(summary = "공지사항 등록")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<NoticeSaveResponse> write(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                                    @RequestBody NoticeSaveRequest noticeSaveRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noticeService.write(noticeSaveRequest, user));
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "공지사항 상세 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 공지사항"),
            @ApiResponse(responseCode = "500", description = "공지사항 상세 조회 실패")
    })
    @Operation(summary = "공지사항 상세")
    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeResponse> read(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                               @PathVariable("noticeId") Long noticeId) {
        return ResponseEntity.ok().body(noticeService.getRead(noticeId, user));
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "공지사항 리스트 조회 성공"),
            @ApiResponse(responseCode = "500", description = "공지사항 리스트 조회 실패")
    })
    @Operation(summary = "공지사항 리스트")
    @GetMapping
    public ResponseEntity<PageResponse> list(@ModelAttribute
                                             @Parameter(description = "page, limit, sortColum, sortStatus 전부 옵션, " +
                                                     "sortColum는 CREATE_DATE, MODIFY_DATE 가능, ASC, DESC 가능")PageRequest pageRequest,
                                             @AuthenticationPrincipal @Parameter(hidden = true) AuthUser user) {
        return ResponseEntity.ok().body(noticeService.getList(pageRequest, user));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "공지사항 삭제 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "403", description = "권한이 없음"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 공지사항"),
            @ApiResponse(responseCode = "500", description = "공지사항 삭제 실패")
    })
    @Operation(summary = "공지사항 삭제")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{noticeId}")
    public void remove(@PathVariable("noticeId") Long noticeId,
                       @AuthenticationPrincipal @Parameter(hidden = true) AuthUser user) {
        noticeService.remove(noticeId, user);
    }

}