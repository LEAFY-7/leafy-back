package bucheon.leafy.application.controller;


import bucheon.leafy.application.service.QnaReplyService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.reply.QnaReplyDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/reply")
public class QnaReplyController {

    private final QnaReplyService qnareplyService;

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "대댓글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "대댓글 수정 실패")
    })
    @Operation(summary = "대댓글 수정")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void modify( @AuthenticationPrincipal AuthUser user,
                        @RequestBody QnaReplyDto qnaReplyDto) {
        Long userId = user.getUserId();
        qnaReplyDto.setUserId(userId);
        qnareplyService.modify(qnaReplyDto);
    }
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "대댓글 쓰기 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "대댓글 쓰기 실패")
    })
    @Operation(summary = "대댓글 쓰기")
    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public void write(@AuthenticationPrincipal AuthUser user,
                      @RequestBody QnaReplyDto qnaReplyDto) {

        Long userId = user.getUserId();
        qnaReplyDto.setUserId(userId);
        qnareplyService.write(qnaReplyDto);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "대댓글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "대댓글 삭제 실패")
    })
    @Operation(summary = "대댓글 삭제")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove( @AuthenticationPrincipal AuthUser user,
                        @PathVariable("id") Long id) {
        Long userId = user.getUserId();
        qnareplyService.remove(id, userId);
    }
}

