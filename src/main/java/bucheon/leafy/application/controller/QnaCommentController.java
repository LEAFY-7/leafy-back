package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.QnaCommentService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.qna.comment.request.QnaCommentEditRequest;
import bucheon.leafy.domain.qna.comment.request.QnaCommentSaveRequest;
import bucheon.leafy.domain.qna.comment.response.QnaCommentEditResponse;
import bucheon.leafy.domain.qna.comment.response.QnaCommentSaveResponse;
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

@Tag( name = "QNA 댓글")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/qna/{qnaId}/comments")
public class QnaCommentController {

    private final QnaCommentService qnacommentService;

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "댓글 수정 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "403", description = "권한이 없음"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 댓글 입니다."),
            @ApiResponse(responseCode = "500", description = "댓글 수정 실패")
    })
    @Operation(summary = "댓글 수정하기")
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{qnaCommentId}")
    public ResponseEntity<QnaCommentEditResponse> modify(@PathVariable("qnaId")  Long qnaId,
                                                         @PathVariable("qnaCommentId")  Long qnaCommentId,
                                                         @AuthenticationPrincipal @Parameter(hidden = true)AuthUser user,
                                                         @RequestBody QnaCommentEditRequest qnaCommentEditRequest ) {

        return ResponseEntity.status(HttpStatus.CREATED).body(qnacommentService.modify(qnaCommentEditRequest, qnaCommentId, user, qnaId));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "댓글 쓰기 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "댓글 삭제 실패")
    })
    @Operation(summary = "댓글 쓰기")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<QnaCommentSaveResponse> write(@PathVariable("qnaId") Long qnaId,
                                                        @AuthenticationPrincipal @Parameter(hidden = true)AuthUser user,
                                                        @RequestBody QnaCommentSaveRequest qnaCommentSaveRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(qnacommentService.write(qnaCommentSaveRequest, user , qnaId));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "댓글 삭제 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 필요"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 Qna"),
            @ApiResponse(responseCode = "500", description = "댓글 삭제 실패")
    })
    @Operation(summary = "댓글 삭제")
    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/{qnaCommentId}")
    public void remove(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                       @PathVariable("qnaId") Long qnaId,
                       @PathVariable("qnaCommentId") Long qnaCommentId) {

        qnacommentService.remove(user, qnaId , qnaCommentId );
    }
}