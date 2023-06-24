package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.QnaReplyService;
import bucheon.leafy.domain.qnareply.QnaReplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class QnaReplyController {

    @Autowired
    QnaReplyService qnaReplyService;
    // 댓글을 수정하는 메서드
    @PatchMapping("/user_id/{cid}")
    public ResponseEntity<String> modify(@PathVariable Integer cid, @RequestBody QnaReplyDto dto) {

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

        Integer user_id = 1111;
        dto.se(user_id);
        dto.setRid(cid);
        System.out.println("dto = " + dto);

        try {
            if(qnaReplyService.write(dto)!=1)
                throw new RuntimeException("Write failed.");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    // 지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/user_id/{cid}")  // DELETE /comments/1?id=1085  <-- 삭제할 댓글 번호
    public ResponseEntity<String> remove(@PathVariable Integer cid,  HttpSession session) {

        String user_id = "asdf";

        try {
            int rowCnt = qnaReplyService.r(cid, cid, user_id);

            if(rowCnt!=1)
                throw new RuntimeException("Delete Failed");

            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/read")
    public String read(Integer cid, RedirectAttributes rattr, Model m) {

        try {
            QnaReplyDto qnaReplyDto = qnaReplyService.read(cid);
            m.addAttribute(qnaReplyDto);
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "READ_ERR");
            return"";
        }

        return "";
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

