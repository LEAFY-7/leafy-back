package bucheon.leafy.application.controller;


import bucheon.leafy.application.service.CommentService;
import bucheon.leafy.application.service.QnaService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.comment.CommentDto;
import bucheon.leafy.exception.ModifyFailedException;;
import bucheon.leafy.exception.RemoveFailedException;
import bucheon.leafy.exception.WriteFailedException;
import io.swagger.v3.oas.annotations.Operation;
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

    private final QnaService qnaService;

    @Operation(summary = "댓글 수정하기")
    @PatchMapping("/modifiy/{id}")
    public ResponseEntity<String> modify(@AuthenticationPrincipal AuthUser user,
                                         @RequestBody CommentDto commentDto) {
        Long userId = user.getUserId();
        commentDto.setUserId(userId);


        if (commentService.modify(commentDto) != 1) {

            throw new ModifyFailedException();
        }

        return new ResponseEntity<>("MOD_OK", HttpStatus.OK);

    }

    @Operation(summary = "댓글 쓰기")
    @PostMapping("/comments/{id}")
    public ResponseEntity<String> write(@AuthenticationPrincipal AuthUser user,
                                        @PathVariable("id") Long id,
                                        @RequestBody CommentDto commentDto) {

        Long userId = user.getUserId();
        commentDto.setUserId(userId);

        if (commentService.write(commentDto) != 1) {
            throw new WriteFailedException();
        }

        qnaService.qnaStatusModify(id);

        return new ResponseEntity<>("WRT_OK", HttpStatus.OK);

    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@AuthenticationPrincipal AuthUser user,
                                         @PathVariable("id") Long id) {

        Long userId = user.getUserId();
        int rowCnt = commentService.remove(id, userId);

        if (rowCnt != 1) {
            throw new RemoveFailedException();
        }

        return ResponseEntity.status(200).body("댓글이 삭제 되었습니다.");
    }
//    @Operation(summary = "댓글 읽기")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<CommentDto> Read(@AuthenticationPrincipal AuthUser user,
//                                       @PathVariable("id") Long id) {
//
//        Long userId = user.getUserId();
//        CommentDto commentDto = commentService.getRead(id, userId);
//
//        if(commentDto) {
//            throw new RemoveFailedException();
//        }

}