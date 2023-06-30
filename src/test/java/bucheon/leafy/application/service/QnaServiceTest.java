//package bucheon.leafy.application.service;
//
//import bucheon.leafy.application.mapper.noticeMapper;

//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class QnaServiceTest {
//
//    @Autowired
//    QnaService qnaService;
//
//    @Autowired
//    noticeMapper noticeMapper;
//
//    @Test
//    void getCount() {
//        noticeMapper.deleteAll ();
//
//        assertTrue(noticeMapper.count()==0);
//
//        QnaDto qnaDto = new QnaDto(1,now, now,"no title", "no content", 11111);
//        assertTrue(noticeMapper.insert(qnaDto)==1);
//        assertTrue(noticeMapper.count()==1);
//
//        assertTrue(noticeMapper.insert(qnaDto)==1);
//        assertTrue(noticeMapper.count()==2);
//    }


//    @Test
//    public void remove()  {
//        noticeMapper.deleteAll();
//
//        QnaDto qnaDto = new QnaDto("hello", "hello", "asdf");
//        assertTrue(noticeMapper.insert(qnaDto) == 1);
//        Integer bno = noticeMapper.selectAll().get(0).getId();
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

//    @Test
//    void getList() {
//    }
//
//    @Test
//    void read() {
//    }
//
//    @Test
//    void getPage() {
//    }
//
//    @Test
//    void modify() {
//    }
//
//    @Test
//    void getSearchResultCnt() {
//    }
//
//    @Test
//    void getSearchSelectPage() {
//    }
//}