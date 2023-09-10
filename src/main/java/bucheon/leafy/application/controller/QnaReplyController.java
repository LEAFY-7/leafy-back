package bucheon.leafy.application.controller;


import bucheon.leafy.application.service.QnaReplyService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.qna.reply.request.QnaReplyEditRequest;
import bucheon.leafy.domain.qna.reply.request.QnaReplySaveRequest;
import bucheon.leafy.domain.qna.reply.response.QnaReplyEditResponse;
import bucheon.leafy.domain.qna.reply.response.QnaReplySaveResponse;
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

@Tag( name = "QNA 대댓글")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/qna/comments/{qnaCommentId}/replies")
public class QnaReplyController {

    private final QnaReplyService qnareplyService;


    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "대댓글 수정 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 대댓글 입니다."),
            @ApiResponse(responseCode = "500", description = "대댓글 수정 실패")
    })
    @Operation(summary = "대댓글 수정")
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{qnaReplyId}")
    public ResponseEntity<QnaReplyEditResponse> modify(@PathVariable("qnaCommentId")  Long qnaCommentId,
                                                       @PathVariable("qnaReplyId")  Long qnaReplyId,
                                                       @AuthenticationPrincipal @Parameter(hidden = true)AuthUser user,
                                                       @RequestBody QnaReplyEditRequest qnaReplyEditRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(qnareplyService.modify(qnaReplyId, qnaReplyEditRequest, user, qnaCommentId));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "대댓글 쓰기 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "대댓글 쓰기 실패")
    })
    @Operation(summary = "대댓글 쓰기")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<QnaReplySaveResponse> write(@PathVariable("qnaCommentId")  Long qnaCommentId,
                                                      @AuthenticationPrincipal @Parameter(hidden = true)AuthUser user,
                                                      @RequestBody QnaReplySaveRequest qnaReplySaveRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(qnareplyService.write(qnaReplySaveRequest, user, qnaCommentId));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "대댓글 삭제 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 Qna"),
            @ApiResponse(responseCode = "500", description = "대댓글 삭제 실패")
    })
    @Operation(summary = "대댓글 삭제")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{qnaReplyId}")
    public void remove( @PathVariable("qnaCommentId")  Long qnaCommentId,
                        @PathVariable("qnaReplyId")  Long qnaReplyId,
                        @AuthenticationPrincipal @Parameter(hidden = true) AuthUser user) {

        qnareplyService.remove(qnaReplyId, user, qnaCommentId);
    }
}

