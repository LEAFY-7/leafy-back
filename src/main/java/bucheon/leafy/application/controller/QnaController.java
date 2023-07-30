package bucheon.leafy.application.controller;

import bucheon.leafy.config.AuthUser;
import bucheon.leafy.application.service.QnaService;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/v1/Qna")
@RequiredArgsConstructor
public class QnaController {
    private final QnaService qnaService;

    @Operation(summary = "Qna 게시물 수정")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @PutMapping("/modify/{id}")
    public ResponseEntity<Object> modify(@AuthenticationPrincipal AuthUser user, @RequestBody QnaDto qnaDto, @PathVariable("id") Long id ) {
        return ResponseEntity.ok().body(qnaService.modify(qnaDto, id));
    }

    @Operation(summary = "Qna 게시판 글 쓰기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @PostMapping("/write")
    public ResponseEntity<Long> write( @AuthenticationPrincipal AuthUser user,@RequestBody QnaDto qnaDto) {
        return ResponseEntity.ok().body(qnaService.write(qnaDto));
    }

    @Operation(summary = "Qna 게시판 클릭 글 읽기")
    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@AuthenticationPrincipal AuthUser user, @PathVariable Long id ) {
        return ResponseEntity.ok().body(qnaService.getRead(id));
    }

    //Mypage이니까 자신만 삭제가능
    @Operation(summary = "Qna 게시판 글 삭제하기")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> remove(@AuthenticationPrincipal AuthUser user, @PathVariable("id") Long id ) {
            return ResponseEntity.ok().body(qnaService.remove(id));

    }

    @Operation(summary = "Mpage에 자신이 올린 Qna 보여주기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<PageResponse<QnaDto>> list(@AuthenticationPrincipal AuthUser user, PageRequest pageRequest) {
        Long userId = user.getUserId();
        if (user.getAuthorities().contains("ROLE_ADMIN")){
            return ResponseEntity.ok().body(qnaService.admingetList(pageRequest));
        }
        return ResponseEntity.ok().body(qnaService.getList(userId, pageRequest));
    }

    @Operation(summary = "Get Me")
    @GetMapping
    public ResponseEntity<GetMeResponse> authorize(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        GetMeResponse getMe = qnaService.getMe(userId);
        return ResponseEntity.ok().body(getMe);
    }
}
