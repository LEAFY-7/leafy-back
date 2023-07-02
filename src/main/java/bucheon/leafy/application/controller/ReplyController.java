package bucheon.leafy.application.controller;


import bucheon.leafy.application.service.ReplyService;
import bucheon.leafy.domain.reply.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class ReplyController {
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

}

