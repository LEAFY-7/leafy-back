package bucheon.leafy.application.controller;


import bucheon.leafy.application.service.QnaCommentService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.comment.request.QnaCommentSaveReqeust;
import bucheon.leafy.domain.comment.response.QnaCommentSaveResponse;
import bucheon.leafy.domain.qna.response.QnaSaveResponse;
import bucheon.leafy.domain.reply.response.QnaReplyEditResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comment")
public class QnaCommentController {

    private final QnaCommentService qnacommentService;

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "댓글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "댓글 수정 실패")
    })
    @Operation(summary = "댓글 수정하기")
    @PatchMapping("/modifiy/{qnaCommentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<QnaReplyEditResponse> modify(@AuthenticationPrincipal AuthUser user,
                                                       @PathVariable("qnaReplyId") Long qnaReplyId,
                                                       @RequestBody String comment ) {
        Long userId = user.getUserId();

        QnaReplyEditResponse response = qnacommentService.modify(qnaReplyId, comment);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "댓글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "댓글 삭제 실패")
    })
    @Operation(summary = "댓글 쓰기")
    @PostMapping("/comments/{qnaCommentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<QnaCommentSaveResponse> write(@AuthenticationPrincipal AuthUser user,
                                        @RequestBody QnaCommentSaveReqeust qnaCommentSaveReqeust) {

        Long userId = user.getUserId();

        QnaCommentSaveResponse response = qnacommentService.write(qnaCommentSaveReqeust);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "댓글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "댓글 삭제 실패")
    })
    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{qnaCommentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void remove(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                       @PathVariable("qnaCommentId") Long qnaCommentId) {

        Long userId = user.getUserId();
        qnacommentService.remove(qnaCommentId, userId);
    }
}