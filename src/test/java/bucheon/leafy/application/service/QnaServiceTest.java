package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.domain.qna.QnaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class QnaServiceTest {

    @Autowired
    QnaService qnaService;

    @Autowired
    QnaMapper qnaMapper;

    @Test
    void getCount()throws Exception {
        qnaMapper.deleteAll ();

        assertTrue(qnaMapper.count()==0);

        QnaDto qnaDto = new QnaDto(1,"no title", "no content", 11111);
        assertTrue(qnaMapper.insert(qnaDto)==1);
        assertTrue(qnaMapper.count()==1);

        assertTrue(qnaMapper.insert(qnaDto)==1);
        assertTrue(qnaMapper.count()==2);
    }


//    @Test
//    public void remove() throws Exception {
//        qnaMapper.deleteAll();
//
//        QnaDto qnaDto = new QnaDto("hello", "hello", "asdf");
//        assertTrue(qnaMapper.insert(qnaDto) == 1);
//        Integer bno = qnaMapper.selectAll().get(0).getId();
//        System.out.println("bno = " + bno);
//
//        commentDao.deleteAll(bno);
//        CommentDto commentDto = new CommentDto(bno,0,"hi","qwer");
//
//        assertTrue(boardDao.select(bno).getComment_cnt() == 0);
//        assertTrue(commentService.write(commentDto)==1);
//        assertTrue(boardDao.select(bno).getComment_cnt() == 1);
//
//        Integer cno = commentDao.selectAll(bno).get(0).getCno();
//
//        // 일부러 예외를 발생시키고 Tx가 취소되는지 확인해야.
//        int rowCnt = commentService.remove(cno, bno, commentDto.getCommenter());
//        assertTrue(rowCnt==1);
//        assertTrue(boardDao.select(bno).getComment_cnt() == 0);
//
//    }
//
//    @Test
//    void write() {
//        boardDao.deleteAll();
//
//        BoardDto boardDto = new BoardDto("hello", "hello", "asdf");
//        assertTrue(boardDao.insert(boardDto) == 1);
//        Integer bno = boardDao.selectAll().get(0).getBno();
//        System.out.println("bno = " + bno);
//
//        commentDao.deleteAll(bno);
//        CommentDto commentDto = new CommentDto(bno,0,"hi","qwer");
//
//        assertTrue(boardDao.select(bno).getComment_cnt() == 0);
//        assertTrue(commentService.write(commentDto)==1);
//
//        Integer cno = commentDao.selectAll(bno).get(0).getCno();
//        assertTrue(boardDao.select(bno).getComment_cnt() == 1);
//    }
//

    @Test
    void getList() {
    }

    @Test
    void read() {
    }

    @Test
    void getPage() {
    }

    @Test
    void modify() {
    }

    @Test
    void getSearchResultCnt() {
    }

    @Test
    void getSearchSelectPage() {
    }
}