package bucheon.leafy.application.controller;

import bucheon.leafy.config.AuthUser;
import bucheon.leafy.application.service.QnaService;
import bucheon.leafy.domain.qna.request.QnaEditRequest;
import bucheon.leafy.domain.qna.request.QnaSaveRequest;
import bucheon.leafy.domain.qna.response.QnaEditResponse;
import bucheon.leafy.domain.qna.response.QnaResponse;
import bucheon.leafy.domain.qna.response.QnaSaveResponse;
import bucheon.leafy.exception.ReadFailedException;
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




@RestController
@RequestMapping("/api/v1/qna")
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
    public ResponseEntity<QnaEditResponse> modify(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user, @PathVariable("qnaId") Long qnaId, @RequestBody QnaEditRequest qnaEditRequest) {

        Long userId = user.getUserId();
        return ResponseEntity.ok().body(qnaService.modify(qnaId, qnaEditRequest));

    }    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Qna 게시판 글 쓰기 성공"),
            @ApiResponse(responseCode = "404", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "Qna 게시파 글 쓰기 실패")
    })
    @Operation(summary = "Qna 게시판 글 쓰기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @PostMapping()
    public ResponseEntity<QnaSaveResponse> write(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user, @RequestBody QnaSaveRequest qnaSaveRequest) {

        Long userId = user.getUserId();

        QnaSaveResponse response = qnaService.write(userId, qnaSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Qna 게시판 글 읽기 성공"),
            @ApiResponse(responseCode = "404", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "Qna 게시판 글 읽기 실패")
    })
    @Operation(summary = "Qna 게시판 클릭 글 읽기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @GetMapping("/{qnaId}")
    public ResponseEntity<QnaResponse> read(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                            @PathVariable Long qnaId) {

        Long userId = user.getUserId();
        return ResponseEntity.ok().body(qnaService.getRead(qnaId));
    }
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Qna 게시판 글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "Qna 게시판 글 삭제 실패")
    })

    //Mypage이니까 자신만 삭제가능
    @Operation(summary = "Qna 게시판 글 삭제하기")
    @DeleteMapping("{qnaId}")
    public ResponseEntity<Object> remove(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                         @PathVariable("qnaId") Long qnaId) {

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
    @GetMapping
    public ResponseEntity<PageResponse> list(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                             @PathVariable("qnaId") Long qnaId,
                                             PageRequest pageRequest) {

        Long userId = user.getUserId();
        Long qnaUserId = qnaService.getQnaById(qnaId);

        if (userId.equals(qnaUserId)) {
            return ResponseEntity.ok().body(qnaService.getList(qnaId, userId, pageRequest));
        } else if (user.getAuthorities().contains("ROLE_ADMIN")) {
            return ResponseEntity.ok().body(qnaService.admingetList(pageRequest));
        }
        throw new ReadFailedException();
    }
}
