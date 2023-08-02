package bucheon.leafy.application.controller;

import bucheon.leafy.config.AuthUser;
import bucheon.leafy.application.service.QnaService;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.exception.enums.ReadFailedException;
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
    @PutMapping("{id}")
    public ResponseEntity<Object> modify(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user, @RequestBody QnaDto qnaDto, @PathVariable("id") Long id) {
        return ResponseEntity.ok().body(qnaService.modify(qnaDto, id));
    }

    @Operation(summary = "Qna 게시판 글 쓰기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @PostMapping("")
    public ResponseEntity<Long> write(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user, @RequestBody QnaDto qnaDto) {
        return ResponseEntity.ok().body(qnaService.write(qnaDto));
    }

    @Operation(summary = "Qna 게시판 클릭 글 읽기")
    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user, @PathVariable Long id) {
        return ResponseEntity.ok().body(qnaService.getRead(id));
    }

    //Mypage이니까 자신만 삭제가능
    @Operation(summary = "Qna 게시판 글 삭제하기")
    @DeleteMapping("{id}")
    public ResponseEntity<Object> remove(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user, @PathVariable("id") Long id) {
        return ResponseEntity.ok().body(qnaService.remove(id));
    }

    @Operation(summary = "Mypage에 자신이 올린 Qna 보여주기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<QnaDto> list(
            @AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
            @PathVariable Long userId, PageRequest pageRequest) {

            userId = user.getUserId();
            QnaDto qnadto = qnaService.getQnaById(userId);


        if (userId.equals(qnadto.getUserId())) {

            return ResponseEntity.ok().body(qnaService.getList(pageRequest));

        } else if (user.getAuthorities().contains("ROLE_ADMIN")) {

            return ResponseEntity.ok().body(qnaService.admingetList(pageRequest));

        }

        throw new ReadFailedException();
    }

    @Operation(summary = "Get Me")
    @GetMapping
    public ResponseEntity<GetMeResponse> authorize(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        GetMeResponse getMe = qnaService.getMe(userId);
        return ResponseEntity.ok().body(getMe);
    }
}
