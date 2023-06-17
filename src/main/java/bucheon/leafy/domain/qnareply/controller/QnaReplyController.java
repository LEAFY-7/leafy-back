package bucheon.leafy.domain.qnareply.controller;

import bucheon.leafy.application.mapper.QnaReplyMapper;
import bucheon.leafy.domain.qna.domain.SearchCondition;
import bucheon.leafy.domain.qnareply.domain.QnaReplyDto;
import bucheon.leafy.domain.qnareply.service.QnaReplyService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "댓글 수정")
    @PatchMapping("/user_id/{cid}")   // /ch4/comments/26  PATCH
    public ResponseEntity<String> modify(@PathVariable Integer rid, @RequestBody QnaReplyDto replyDto, HttpSession session) {
        String user_id = (String)session.getAttribute("id");
        replyDto.setUser_id(user_id);
        replyDto.setId(rid);

        try {
            if(qnaReplyService.modify(replyDto)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "댓글 쓰기")
    @PostMapping("/user_id")   // /ch4/comments?id=1085  POST
    public ResponseEntity<String> write(@RequestBody QnaReplyDto replyDto, Integer rid, HttpSession session) {
        String user_id = (String)session.getAttribute("id");
        replyDto.setUser_id(user_id);
        replyDto.setRid(rid);

        try {
            if(qnaReplyService.write(replyDto)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/user_id/{cid}")
    public ResponseEntity<String> remove(@PathVariable Integer rid, Integer reply_reply, HttpSession session) {
        String user_id = (String)session.getAttribute("id");

        try {
            int rowCnt = qnaReplyService.remove(rid, reply_reply, user_id);

            if(rowCnt!=1)
                throw new Exception("Delete Failed");

            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "대댓글 읽기")
    @GetMapping("/read")
    public String read(Integer reply_reply, SearchCondition sc, RedirectAttributes rattr, Model m) {
        try {
            QnaReplyDto qnaReplyDto = qnaReplyService.read(reply_reply);
            m.addAttribute(qnaReplyDto);
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "READ_ERR");
            return "" + sc.getQueryString();
        }
        return "board";
    }
    @Operation(summary = "게시물 댓글 가지고 오기")
    @GetMapping("/user_id")
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

