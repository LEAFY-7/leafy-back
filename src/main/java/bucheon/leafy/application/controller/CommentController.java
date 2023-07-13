package bucheon.leafy.application.controller;



import bucheon.leafy.application.service.CommentService;
import bucheon.leafy.application.service.QnaService;
import bucheon.leafy.application.service.ReplyService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.comment.CommentDto;
import bucheon.leafy.exception.ModifyFailedException;
import bucheon.leafy.exception.enums.ReadFailedException;
import bucheon.leafy.exception.enums.RemoveFailedException;
import bucheon.leafy.exception.enums.WriteFailedException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/comment")
public class CommentController {

    private final CommentService commentService;
    private final QnaService qnaService;
    private final ReplyService replyService;

    @Operation(summary = "댓글 수정하기")
    @PatchMapping("/modifiy/{id}")   // /ch4/comments/26  PATCH
    public ResponseEntity<String> modify( @AuthenticationPrincipal AuthUser user,
                                          @PathVariable("id") Long id,
                                          @RequestBody CommentDto commentDto) {
        Long userId = user.getUserId();
        commentDto.setUserId(userId);



            if(commentService.modify(commentDto)!=1){

                throw new ModifyFailedException();
            }

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);

    }
    @Operation(summary = "댓글 쓰기")
    @PostMapping("/comments/{id}")   // /ch4/comments?bno=1085  POST
    public ResponseEntity<String> write( @AuthenticationPrincipal AuthUser user,
                                         @PathVariable("id") Long id,
                                         @RequestBody CommentDto commentDto) {

        Long userId = user.getUserId();
        commentDto.setUserId(userId);

            if(commentService.write(commentDto)!=1){
                throw new WriteFailedException();
            }
            commentService.sendEmailNotification(commentDto);
            qnaService.qnaStatusModify(id);
            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);

    }


    @Operation(summary = "Qna 게시판 댓글 읽기")
    @GetMapping("/read/{id}/comment/{commentId}")
    public ResponseEntity<Object> readComment(
            @PathVariable("id") Long id
    ) {

        Long commentId = replyService.getRepliesByCommentId(id);
        List<CommentDto> commentDtoList =  commentService.getRead(commentId);

        if(commentId == 0){
            throw  new ReadFailedException();
        }
        return ResponseEntity.ok().body(commentDtoList);

    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove( @AuthenticationPrincipal AuthUser user,
                                          @PathVariable("id") Long id) {

        Long userId = user.getUserId();

        int rowCnt = commentService.remove(id, userId);

        if(rowCnt != 1) {
            throw new RemoveFailedException();
        }

        return ResponseEntity.status(200).body("댓글이 삭제 되었습니다.");
    }



}

