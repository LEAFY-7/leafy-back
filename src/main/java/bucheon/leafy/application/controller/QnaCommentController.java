package bucheon.leafy.application.controller;


import bucheon.leafy.application.service.QnaCommentService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.comment.QnaCommentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/comment")
public class QnaCommentController {

    private final QnaCommentService qnacommentService;

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "댓글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "댓글 수정 실패")
    })
    @Operation(summary = "댓글 수정하기")
    @PatchMapping("/modifiy/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void modify(@AuthenticationPrincipal AuthUser user,
                       @RequestBody QnaCommentDto qnacommentDto) {
        Long userId = user.getUserId();
        qnacommentDto.setUserId(userId);
        qnacommentService.modify(qnacommentDto);

    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "댓글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "댓글 삭제 실패")
    })
    @Operation(summary = "댓글 쓰기")
    @PostMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void write(@AuthenticationPrincipal AuthUser user,
                      @RequestBody QnaCommentDto qnaCommentDto) {

        Long userId = user.getUserId();
        qnaCommentDto.setUserId(userId);
        qnacommentService.write(qnaCommentDto);

    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "댓글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "댓글 삭제 실패")
    })
    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void remove(@AuthenticationPrincipal AuthUser user,
                       @PathVariable("qnaCommentId") Long qnaCommentId) {

        Long userId = user.getUserId();
        qnacommentService.remove(qnaCommentId, userId);
    }
}