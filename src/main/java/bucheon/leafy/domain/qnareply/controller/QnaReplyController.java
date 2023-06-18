package bucheon.leafy.domain.qnareply.controller;

import bucheon.leafy.application.mapper.QnaReplyMapper;
import bucheon.leafy.domain.qnareply.domain.QnaReplyDto;
import bucheon.leafy.domain.qnareply.service.QnaReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class QnaReplyController {

    @Autowired
    QnaReplyService qnaReplyService;
    // 댓글을 수정하는 메서드
    @PatchMapping("/user_id/{cid}")   // /ch4/comments/26  PATCH
    public ResponseEntity<String> modify(@PathVariable Integer cid, @RequestBody QnaReplyDto dto) {
//        String user_id = (String)session.getAttribute("id");
        Integer user_id = 1111;
        dto.setUser_id(user_id);
        System.out.println("dto = " + dto);

        try {
            if(qnaReplyService.modify(dto)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/user_id")   // /ch4/comments?id=1085  POST
    public ResponseEntity<String> write(@RequestBody QnaReplyDto dto, Integer cid, HttpSession session) {
//        String user_id = (String)session.getAttribute("id");
        Integer user_id = 1111;
        dto.setUser_id(user_id);
        dto.setRid(cid);
        System.out.println("dto = " + dto);

        try {
            if(qnaReplyService.write(dto)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    // 지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/user_id/{cid}")  // DELETE /comments/1?id=1085  <-- 삭제할 댓글 번호
    public ResponseEntity<String> remove(@PathVariable Integer cid,  HttpSession session) {
//        String user_id = (String)session.getAttribute("id");
        String user_id = "asdf";

        try {
            int rowCnt = qnaReplyService.remove(cid, cid, user_id);

            if(rowCnt!=1)
                throw new Exception("Delete Failed");

            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    // 지정된 게시물의 모든 댓글을 가져오는 메서드
    @GetMapping("/user_id")  // /comments?id=1080   GET
    public ResponseEntity<List<QnaReplyDto>> list(Integer cid) {
        List<QnaReplyDto> list = null;
        try {
            list = qnaReplyService.getList(cid);
            return new ResponseEntity<List<QnaReplyDto>>(list, HttpStatus.OK);  // 200
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<QnaReplyDto>>(HttpStatus.BAD_REQUEST); // 400
        }
    }

}

