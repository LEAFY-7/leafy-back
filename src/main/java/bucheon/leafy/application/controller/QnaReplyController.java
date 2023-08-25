package bucheon.leafy.application.controller;


import bucheon.leafy.application.service.QnaReplyService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.reply.request.QnaReplyEditReqeust;
import bucheon.leafy.domain.reply.request.QnaReplySaveReqeust;
import bucheon.leafy.domain.reply.response.QnaReplyResponse;
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
@RequestMapping("/api/v1/reply")
public class QnaReplyController {

    private final QnaReplyService qnareplyService;


    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Qna 게시판 글 읽기 성공"),
            @ApiResponse(responseCode = "404", description = "로그인 필요"),
            @ApiResponse(responseCode = "500", description = "Qna 게시판 글 읽기 실패")
    })
    @Operation(summary = "QnaReply 게시판 클릭 글 읽기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @GetMapping("/{qnaReplyId}")
    public ResponseEntity<QnaReplyResponse> read(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user, @PathVariable Long qnaCommentId) {

        Long userId = user.getUserId();
        return ResponseEntity.ok().body(qnareplyService.getRead(qnaCommentId));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "대댓글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "대댓글 수정 실패")
    })
    @Operation(summary = "대댓글 수정")
    @ResponseStatus(HttpStatus.CREATED)
    public void modify( @AuthenticationPrincipal AuthUser user,
                        @RequestBody QnaReplyEditReqeust qnaReplyEditReqeust) {
        Long userId = user.getUserId();
        qnaReplyEditReqeust.setUserId(userId);
        qnareplyService.modify(qnaReplyEditReqeust);
    }
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "대댓글 쓰기 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "대댓글 쓰기 실패")
    })
    @Operation(summary = "대댓글 쓰기")
    @PostMapping("/qnaReplyId")
    @ResponseStatus(HttpStatus.CREATED)
    public void write(@AuthenticationPrincipal AuthUser user,
                      @RequestBody QnaReplySaveReqeust qnaReplySaveReqeust) {

        Long userId = user.getUserId();
        qnaReplySaveReqeust.setUserId(userId);
        qnareplyService.write(qnaReplySaveReqeust);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "대댓글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "대댓글 삭제 실패")
    })
    @Operation(summary = "대댓글 삭제")
    @DeleteMapping("/{qnaReplyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove( @AuthenticationPrincipal AuthUser user,
                        @PathVariable("qnaReplyId") Long qnaReplyId) {
        Long userId = user.getUserId();
        qnareplyService.remove(qnaReplyId, userId);
    }
}

