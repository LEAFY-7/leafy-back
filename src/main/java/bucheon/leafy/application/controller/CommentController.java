package bucheon.leafy.application.controller;



import bucheon.leafy.application.service.CommentService;
import bucheon.leafy.domain.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;



@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/comment")
public class CommentController {
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

}

