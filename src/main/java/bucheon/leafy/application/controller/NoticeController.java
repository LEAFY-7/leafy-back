package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.NoticeService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/notice")
public class NoticeController {

    private final NoticeService noticeService;
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Notice 글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "Notice 글 수정 실패")
    })
    @Operation(summary = "Notice 게시판 글 수정하기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{noticeId}")
    public ResponseEntity<Object> modify(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
            @RequestBody NoticeDto noticeDto) {

        Long userId = user.getUserId();
        return ResponseEntity.ok().body(noticeService.modify(noticeDto));
    }
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Notice 글 작성 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "Notice 글 작성 실패")
    })
    @Operation(summary = "Notice 게시판에 글 작성하기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public void write(
            @AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser,
            @RequestBody NoticeDto noticeDto

    ) {
        Long userId = authUser.getUserId();
        noticeService.write(userId,noticeDto);
    }
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Notice 글 읽기  성공"),
            @ApiResponse(responseCode = "500", description = "Notice 글 읽기 실패")
    })
    @Operation(summary = "Notice 게시판 클릭 글 읽기")
    @GetMapping("/{noticeId}")
    public ResponseEntity<Object> read( @PathVariable("noticeId")  Long noticeId) {
        return ResponseEntity.ok().body(noticeService.getRead(noticeId));
    }
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Notice 글 목록 보여주기 성공"),
            @ApiResponse(responseCode = "500", description = "Notice 글 목록 보여주기 실패")
    })
    @Operation(summary = "Notice 게시판의 전체 글 목록 보기")
    @GetMapping
    public ResponseEntity<PageResponse> list(PageRequest pageRequest) {
        return ResponseEntity.ok().body(noticeService.getList(pageRequest));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Notice 글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "Notice 글 삭제 실패")
    })
    @Operation(summary = "Notice 게시판 글 삭제하기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @DeleteMapping("/{noticeId}")
    public ResponseEntity<Object> remove(@PathVariable("noticeId") Long noticeId,
                                         @AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser
            ) {
        Long userId = authUser.getUserId();
        //모든관리자가 삭제할수있을려면 userId로 하면 x
        return ResponseEntity.ok().body(noticeService.remove(noticeId));
    }
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Notice Get Me 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "Notice Get Me 실패")
    })
    @Operation(summary = "Get Me")
    @GetMapping("/me") // 엔드포인트를 /v1/me로 수정합니다.
    public ResponseEntity<GetMeResponse> authorize(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        GetMeResponse getMe = noticeService.getMe(userId);
        return ResponseEntity.ok().body(getMe);
    }
}
