package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.NoticeService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.user.response.GetMeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "Notice 게시판 글 수정하기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> modify(
            @RequestBody NoticeDto noticeDto) {
        return ResponseEntity.ok().body(noticeService.modify(noticeDto));
    }

    @Operation(summary = "Notice 게시판에 글 작성하기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Long> write(
            @RequestBody NoticeDto noticeDto
    ) {
        return ResponseEntity.ok().body(noticeService.write(noticeDto));
    }

    @Operation(summary = "Notice 게시판 클릭 글 읽기")
    @GetMapping("/{id}")
    public ResponseEntity<Object> read( @PathVariable Long id) {
        return ResponseEntity.ok().body(noticeService.getRead(id));
    }

    @Operation(summary = "Notice 게시판의 전체 글 목록 보기")
    @GetMapping
    public ResponseEntity<List<NoticeDto>> list() {
        return ResponseEntity.ok().body(noticeService.getList());
    }

    @Operation(summary = "Notice 게시판 글 삭제하기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable("id") Long id) {
      //모든관리자가 삭제할수있을려면 userId로 하면 x
        return ResponseEntity.ok().body(noticeService.remove(id));
    }
    @Operation(summary = "Get Me")
    @GetMapping("/me") // 엔드포인트를 /v1/me로 수정합니다.
    public ResponseEntity<GetMeResponse> authorize(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        GetMeResponse getMe = noticeService.getMe(userId);
        return ResponseEntity.ok().body(getMe);
    }
}
