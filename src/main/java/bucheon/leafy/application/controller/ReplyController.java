package bucheon.leafy.application.controller;


import bucheon.leafy.application.service.CommentService;
import bucheon.leafy.application.service.ReplyService;
import bucheon.leafy.config.AuthUser;


import bucheon.leafy.domain.reply.ReplyDto;
import bucheon.leafy.exception.ModifyFailedException;
import bucheon.leafy.exception.RemoveFailedException;
import bucheon.leafy.exception.WriteFailedException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/reply")
public class ReplyController {

    @Autowired
    ReplyService replyService;
    private final CommentService commentService;

    @Operation(summary = "대댓글 수정")
    @PatchMapping("/{id}")  //수정
    public ResponseEntity<String> modify( @AuthenticationPrincipal AuthUser user,
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
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove( @AuthenticationPrincipal AuthUser user,
                                          @PathVariable("id") Long id) {

        Long userId = user.getUserId();
        int rowCnt = replyService.remove(id, userId);

        if(rowCnt != 1) {
            throw new RemoveFailedException();
        }
        return ResponseEntity.status(200).body("댓글이 삭제 되었습니다.");
    }
}

