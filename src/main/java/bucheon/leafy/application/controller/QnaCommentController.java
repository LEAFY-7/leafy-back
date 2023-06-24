package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.QnaCommentService;
import bucheon.leafy.domain.qna.QnaCommentDto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class QnaCommentController {

    @Autowired
    QnaCommentService qnaCommentService;
    // 댓글을 수정하는 메서드
    @PatchMapping("/user_id/{cid}")   // /ch4/comments/26  PATCH
    public ResponseEntity<String> modify(@PathVariable Integer cid, @RequestBody QnaCommentDto dto) {
//        String user_id = (String)session.getAttribute("id");
        Integer user_id = 1111;
        dto.se(user_id);
        System.out.println("dto = " + dto);

        try {
            if(qnaCommentService.modify(dto)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/user_id")   // /ch4/comments?id=1085  POST
    public ResponseEntity<String> write(@RequestBody QnaCommentDto dto, Integer cid, HttpSession session) {
//        String user_id = (String)session.getAttribute("id");
        Integer user_id = 1111;
        dto.se(user_id);
        dto.setRid(cid);
        System.out.println("dto = " + dto);

        try {
            if(qnaCommentService.write(dto)!=1)
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
            int rowCnt = qnaCommentService.(cid, cid, user_id);

            if(rowCnt!=1)
                throw new Exception("Delete Failed");

            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }
//    @GetMapping("/read")
//    public String read(Integer cid, RedirectAttributes rattr, Model m) {
//
//        try {
//            QnaCommentDto qnaCommentDto = qnaCommentService.read(cid);
//            m.addAttribute(qnaCommentDto);
//        } catch (Exception e) {
//            e.printStackTrace();
//            rattr.addFlashAttribute("msg", "READ_ERR");
//            return"";
//        }
//        QnaCommentDto qnaCommentMapper
//        return "";
//    }

    // 지정된 게시물의 모든 댓글을 가져오는 메서드
    @GetMapping("/user_id")  // /comments?id=1080   GET
    public ResponseEntity<List<QnaCommentDto>> list(Integer cid) {
        List<QnaCommentDto> list = null;
        try {
            list = qnaCommentService.getList(cid);
            return new ResponseEntity<List<QnaCommentDto>>(list, HttpStatus.OK);  // 200
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<QnaCommentDto>>(HttpStatus.BAD_REQUEST); // 400
        }
    }

}

