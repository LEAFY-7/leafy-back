package bucheon.leafy.application.controller;


import bucheon.leafy.application.service.QnaCommentService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.comment.request.QnaCommentEditRequest;
import bucheon.leafy.domain.comment.request.QnaCommentSaveRequest;
import bucheon.leafy.domain.comment.response.QnaCommentEditResponse;
import bucheon.leafy.domain.comment.response.QnaCommentSaveResponse;
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
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{qnaCommentId}")
    public ResponseEntity<QnaCommentEditResponse> modify(@PathVariable("qnaReplyId") Long qnaReplyId,
                                                         @AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                                         @RequestBody QnaCommentEditRequest qnaCommentEditRequest ) {
        Long userId = user.getUserId();

        QnaCommentEditResponse response = qnacommentService.modify(qnaReplyId, qnaCommentEditRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "댓글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "댓글 삭제 실패")
    })
    @Operation(summary = "댓글 쓰기")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<QnaCommentSaveResponse> write(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                        @RequestBody QnaCommentSaveRequest qnaCommentSaveRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(qnacommentService.write(user ,qnaCommentSaveRequest));
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