package bucheon.leafy.application.controller;


import bucheon.leafy.application.service.CommentService;
import bucheon.leafy.application.service.QnaService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.comment.CommentDto;
import bucheon.leafy.exception.ModifyFailedException;;
import bucheon.leafy.exception.RemoveFailedException;
import bucheon.leafy.exception.WriteFailedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "댓글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "유효하지 않은 회원 ID"),
            @ApiResponse(responseCode = "500", description = "댓글 수정 실패")
    })
    @Operation(summary = "댓글 수정하기")
    @PatchMapping("/modifiy/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void modify(@AuthenticationPrincipal AuthUser user,
                                         @RequestBody CommentDto commentDto) {
        Long userId = user.getUserId();
        commentDto.setUserId(userId);
        commentService.modify(commentDto);

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
                                        @PathVariable("id") Long id,
                                        @RequestBody CommentDto commentDto) {

        Long userId = user.getUserId();
        commentDto.setUserId(userId);
        commentService.write(commentDto);

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
                                         @PathVariable("id") Long id) {

        Long userId = user.getUserId();
        commentService.remove(id, userId);
    }
}