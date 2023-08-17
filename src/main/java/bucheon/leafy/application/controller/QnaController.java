package bucheon.leafy.application.controller;

import bucheon.leafy.config.AuthUser;
import bucheon.leafy.application.service.QnaService;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.exception.ReadFailedException;
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


@RestController
@RequestMapping("/v1/Qna")
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Qna 게시글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "유저의 알림 삭제 실패")
    })
    @Operation(summary = "Qna 게시물 수정")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @PutMapping("{qnaId}")
    public ResponseEntity<Object> modify(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user, @RequestBody QnaDto qnaDto, @PathVariable("id") Long qnaId) {

        Long userId = user.getUserId();
        return ResponseEntity.ok().body(qnaService.modify(qnaDto, qnaId));

    }    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Qna 게시판 글 쓰기 성공"),
            @ApiResponse(responseCode = "404", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "Qna 게시파 글 쓰기 실패")
    })
    @Operation(summary = "Qna 게시판 글 쓰기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @PutMapping
    public ResponseEntity<Long> write(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user, @RequestBody QnaDto qnaDto) {

        Long userId = user.getUserId();
        return ResponseEntity.ok().body(qnaService.write(qnaDto));
    }
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Qna 게시판 글 읽기 성공"),
            @ApiResponse(responseCode = "404", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "Qna 게시판 글 읽기 실패")
    })
    @Operation(summary = "Qna 게시판 클릭 글 읽기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @GetMapping("/{qnaId}")
    public ResponseEntity<Object> read(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,@PathVariable Long qnaId) {

        Long userId = user.getUserId();
        return ResponseEntity.ok().body(qnaService.getRead(qnaId, userId));
    }
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Qna 게시판 글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "Qna 게시판 글 삭제 실패")
    })

    //Mypage이니까 자신만 삭제가능
    @Operation(summary = "Qna 게시판 글 삭제하기")
    @DeleteMapping("{qnaId}")
    public ResponseEntity<Object> remove(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user, @PathVariable("qnaId") Long qnaId) {

        Long userId = user.getUserId();
        return ResponseEntity.ok().body(qnaService.remove(qnaId));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Qna List 보여주기 성공"),
            @ApiResponse(responseCode = "404", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "Qna List 보여주기 실패")
    })
    @Operation(summary = "Mypage에 자신이 올린 Qna 보여주기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<PageResponse> list(
            @AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
            @PathVariable Long qnaId, PageRequest pageRequest) {

        Long userId = user.getUserId();
        QnaDto qnadto = qnaService.getQnaById(userId);

        if (userId.equals(qnadto.getUserId())) {
            return ResponseEntity.ok().body(qnaService.getList(qnaId, userId, pageRequest));
        } else if (user.getAuthorities().contains("ROLE_ADMIN")) {
            return ResponseEntity.ok().body(qnaService.admingetList(pageRequest));
        }
        throw new ReadFailedException();
    }
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Qna Get Me 성공"),
            @ApiResponse(responseCode = "404", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "Qna Get Me 실패")
    })
    @Operation(summary = "Get Me")
    @GetMapping
    public ResponseEntity<GetMeResponse> authorize(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {

        Long userId = authUser.getUserId();
        GetMeResponse getMe = qnaService.getMe(userId);
        return ResponseEntity.ok().body(getMe);
    }
}
