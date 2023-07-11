package bucheon.leafy.application.controller;



import bucheon.leafy.application.service.CommentService;
<<<<<<< HEAD
import bucheon.leafy.application.service.EmailService;
import bucheon.leafy.application.service.QnaService;
import bucheon.leafy.application.service.ReplyService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.comment.CommentDto;
import bucheon.leafy.domain.qna.QnaReply;
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
=======
import bucheon.leafy.domain.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/comment")
public class CommentController {
<<<<<<< HEAD

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



=======
    @Autowired
    CommentService commentService;
    @PatchMapping("/comments/{cno}")   // /ch4/comments/26  PATCH
    public ResponseEntity<String> modify(@PathVariable Long id, @RequestBody CommentDto commentDto) {

        String commenter = "asdf";
//        commentDto.setUserId(commenter);

        try {
            if(commentService.modify(commentDto)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/comments")   // /ch4/comments?bno=1085  POST
    public ResponseEntity<String> write(@RequestBody CommentDto commentDto, Long qnaId, HttpSession session) {
        Long userId = 1111L;
        commentDto.setUserId(userId);
        commentDto.setQnaId(qnaId);

        try {
            if(commentService.write(commentDto)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    // 지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/comments/{cno}")  // DELETE /comments/1?bno=1085  <-- 삭제할 댓글 번호
    public ResponseEntity<String> remove(@PathVariable Long id, Long qnaId, HttpSession session) {
//        String commenter = (String)session.getAttribute("id");
        Long userId = 11111L;

        try {
            int rowCnt = commentService.remove(id, userId);

            if(rowCnt!=1)
                throw new Exception("Delete Failed");

            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }

>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
}

