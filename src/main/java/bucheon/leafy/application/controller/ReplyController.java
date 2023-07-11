package bucheon.leafy.application.controller;


import bucheon.leafy.application.service.ReplyService;
<<<<<<< HEAD
import bucheon.leafy.config.AuthUser;


import bucheon.leafy.domain.reply.ReplyDto;
import bucheon.leafy.exception.ModifyFailedException;
import bucheon.leafy.exception.enums.RemoveFailedException;
import bucheon.leafy.exception.enums.WriteFailedException;
import io.swagger.v3.oas.annotations.Operation;
=======
import bucheon.leafy.domain.reply.ReplyDto;
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
=======
import org.springframework.web.bind.annotation.*;

>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57


@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class ReplyController {
<<<<<<< HEAD

    @Autowired
    ReplyService replyService;

    @Operation(summary = "대댓글 수정")
    @PatchMapping("/{id}")   // /ch4/comments/26  PATCH
    public ResponseEntity<String> modify( @AuthenticationPrincipal AuthUser user,
                                            @PathVariable("id") Long id,
                                            @RequestBody ReplyDto replyDto) {
        Long userId = user.getUserId();
        replyDto.setUserId(userId);


            if(replyService.modify(replyDto)!=1) {
                throw new ModifyFailedException();
            }
            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);

    }
    @Operation(summary = "대댓글 쓰기")
    @PostMapping("/comments")   // /ch4/comments?bno=1085  POST
    public ResponseEntity<String> write( @AuthenticationPrincipal AuthUser user,
                                         @RequestBody ReplyDto replyDto) {

        Long userId = user.getUserId();
        replyDto.setUserId(userId);


        if (replyService.write(replyDto) != 1){
            throw new WriteFailedException();
        }
            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);

    }

    @Operation(summary = "대댓글 삭제")
    @GetMapping("/{id}")
    public ResponseEntity<Object> read( @AuthenticationPrincipal AuthUser user,
                                        @PathVariable("id") Long id ) {


//        Long userId = user.getUserId();

        if (replyService.getRead(id).size() != 1) {
            throw new RemoveFailedException();
        } else {
            List<ReplyDto> replyDtoList = replyService.getRead(id);
            ReplyDto replyDto = replyDtoList.get(0);
            return ResponseEntity.ok().body(replyDto);
        }
    }
=======
    @Autowired
    ReplyService replyService;
    @PatchMapping("")
    public ResponseEntity<String> modify(@PathVariable Long id,
                                         @RequestBody ReplyDto replyDto) {

        String commenter = "asdf";
//        commentDto.setUserId(commenter);

        try {
            if(replyService.modify(replyDto)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/comments")   // /ch4/comments?bno=1085  POST
    public ResponseEntity<String> write(@RequestBody ReplyDto replyDto, Long qnaCommentId) {
        Long userId = 1111L;
        replyDto.setUserId(userId);
        replyDto.setQnaCommentId(qnaCommentId);

        try {
            if(replyService.write(replyDto)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    // 지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/comments/{cno}")  // DELETE /comments/1?bno=1085  <-- 삭제할 댓글 번호
    public ResponseEntity<String> remove(@PathVariable Long id) {
//        String commenter = (String)session.getAttribute("id");
        Long userId = 11111L;

        try {
            int rowCnt = replyService.remove(id,  userId);

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

