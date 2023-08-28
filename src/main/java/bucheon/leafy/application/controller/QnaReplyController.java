package bucheon.leafy.application.controller;


import bucheon.leafy.application.service.QnaReplyService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.reply.request.QnaReplySaveRequest;
import bucheon.leafy.domain.reply.response.QnaReplyResponse;
import bucheon.leafy.domain.reply.response.QnaReplySaveResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag( name = "QNA 대댓글")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/qna/{qnaId}/comments/{qnaCommentId}/replies")
public class QnaReplyController {

    private final QnaReplyService qnareplyService;


    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Qna 게시판 글 읽기 성공"),
            @ApiResponse(responseCode = "404", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "Qna 게시판 글 읽기 실패")
    })
    @Operation(summary = "QnaReply 게시판 클릭 글 읽기")
    @GetMapping("/{qnaReplyId}")
    public ResponseEntity<QnaReplyResponse> read(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                                 @PathVariable Long qnaReplyId) {

        Long userId = user.getUserId();
        return ResponseEntity.ok().body(qnareplyService.getRead(qnaReplyId));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "대댓글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "대댓글 수정 실패")
    })
    @Operation(summary = "대댓글 수정")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @PutMapping("/{qnaReplyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void modify( @AuthenticationPrincipal AuthUser user,
                        @PathVariable("qnaReplyId") Long qnaReplyId,
                        @RequestBody String comment) {
        Long userId = user.getUserId();
        qnareplyService.modify(qnaReplyId, comment);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "대댓글 쓰기 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "대댓글 쓰기 실패")
    })
    @Operation(summary = "대댓글 쓰기")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @PostMapping("/{qnaReplyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<QnaReplySaveResponse> write(@AuthenticationPrincipal AuthUser user,
                                                      @RequestBody QnaReplySaveRequest qnaReplySaveRequest) {

        Long userId = user.getUserId();
        QnaReplySaveResponse response = qnareplyService.write(qnaReplySaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "대댓글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "대댓글 삭제 실패")
    })
    @Operation(summary = "대댓글 삭제")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @DeleteMapping("/{qnaReplyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove( @AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                        @PathVariable("qnaReplyId") Long qnaReplyId) {
        Long userId = user.getUserId();
        qnareplyService.remove(qnaReplyId);
    }
}

