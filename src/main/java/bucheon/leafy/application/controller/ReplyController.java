package bucheon.leafy.application.controller;


<<<<<<< HEAD
import bucheon.leafy.application.service.CommentService;
=======
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
import bucheon.leafy.application.service.ReplyService;
import bucheon.leafy.config.AuthUser;


import bucheon.leafy.domain.reply.ReplyDto;
import bucheon.leafy.exception.ModifyFailedException;
<<<<<<< HEAD
import bucheon.leafy.exception.RemoveFailedException;
import bucheon.leafy.exception.WriteFailedException;
=======
import bucheon.leafy.exception.enums.RemoveFailedException;
import bucheon.leafy.exception.enums.WriteFailedException;
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde


@RequiredArgsConstructor
@RestController
<<<<<<< HEAD
@RequestMapping("/v1/reply")
=======
@RequestMapping("/")
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
public class ReplyController {

    @Autowired
    ReplyService replyService;
<<<<<<< HEAD
    private final CommentService commentService;

    @Operation(summary = "대댓글 수정")
    @PatchMapping("/{id}")  //수정
    public ResponseEntity<String> modify( @AuthenticationPrincipal AuthUser user,
=======

    @Operation(summary = "대댓글 수정")
    @PatchMapping("/{id}")   // /ch4/comments/26  PATCH
    public ResponseEntity<String> modify( @AuthenticationPrincipal AuthUser user,
                                            @PathVariable("id") Long id,
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
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

<<<<<<< HEAD
=======

>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
        if (replyService.write(replyDto) != 1){
            throw new WriteFailedException();
        }
            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);

    }

<<<<<<< HEAD

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
=======
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
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
    }
}

