<<<<<<< HEAD
package bucheon.leafy.application.service;//package bucheon.leafy.application.service;
//
//import bucheon.leafy.application.mapper.QnaMapper;
//import bucheon.leafy.domain.qna.QnaDto;
//import bucheon.leafy.domain.qna.SearchHandler;
=======
//package bucheon.leafy.application.service;
//
//import bucheon.leafy.application.mapper.noticeMapper;

>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//
<<<<<<< HEAD
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
=======
//import java.util.List;
//import java.util.Map;
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class QnaServiceTest {
//
//    @Autowired
<<<<<<< HEAD
//    private QnaService qnaService;
//
//    @Autowired
//    private QnaMapper qnaMapper;
//
//    // 현재 시간
//    @Test
//    void count() {
//        qnaMapper.deleteAll();
//        assertEquals(0, qnaService.getCount());
//
//        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaService.write(qnaDto));
//        assertEquals(1, qnaService.getCount());
//
//        assertEquals(1, qnaService.write(qnaDto));
//        assertEquals(2, qnaService.getCount());
//    }
//
//    @Test
//    void deleteAll() {
//        qnaMapper.deleteAll();
//        assertEquals(0, qnaService.getCount());
//
//        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaService.write(qnaDto));
//
//        assertEquals(1, qnaService.getCount());
//
//        qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaService.write(qnaDto));
//        assertEquals(1, qnaService.write(qnaDto));
//
//        assertEquals(3, qnaService.getCount());
//    }
//
//    @Test
//    void deleteTest() {
//        qnaMapper.deleteAll();
//        assertEquals(0, qnaService.getCount());
//
//        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaService.write(qnaDto));
//        Long id = qnaService.getList().get(0).getId();
//        assertEquals(1, qnaService.remove(id, qnaDto.getUserId()));
//
//        QnaDto qnaDtoList = qnaService.getRead(id);
//
//        assert true == qnaDtoList.get(0).getIsDelete();
=======
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
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
//
//    }
//
//    @Test
<<<<<<< HEAD
//    void insertTest() {
//        qnaMapper.deleteAll();
//        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaService.write(qnaDto));
//
//        qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaService.write(qnaDto));
//        assertEquals(2, qnaService.getCount());
//
//        qnaMapper.deleteAll();
//        qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaService.write(qnaDto));
//        assertEquals(1, qnaService.getCount());
//    }
//
//    @Test
//    void selectAllTest() {
//        qnaMapper.deleteAll();
//        assertEquals(0, qnaService.getCount());
//
//        List<QnaDto> list = qnaService.getList();
//        assertEquals(0, list.size());
//
//        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaService.write(qnaDto));
//
//        list = qnaService.getList();
//        assertEquals(1, list.size());
//
//        assertEquals(1, qnaService.write(qnaDto));
//        list = qnaService.getList();
//        assertEquals(2, list.size());
//    }
//
//    @Test
//    void selectTest() {
//        qnaMapper.deleteAll();
//        assertEquals(0, qnaService.getCount());
//
//        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaService.write(qnaDto));
//
//        Long id = qnaService.getList().get(0).getId();
//        QnaDto qnaDtoList = qnaService.getRead(id);
//
//        assertFalse(qnaDtoList.isEmpty());
//        assertTrue(compareRelevantFields(qnaDto, qnaDtoList.get(0)));
//    }
//
//    @Test
//    void selectPageTest() {
//        qnaMapper.deleteAll();
//
//        for (int i = 1; i <= 10; i++) {
//            QnaDto qnaDto = new QnaDto("dfsgf", ""+i, 11111L);
//            qnaService.write(qnaDto);
//        }
//
//        SearchHandler searchHandler = new SearchHandler(1,5);
//
//        List<QnaDto> list = qnaService.getSearchResultPage(searchHandler);
//        assertEquals("10", list.get(0).getTitle());
//        assertEquals("9", list.get(1).getTitle());
//        assertEquals("8", list.get(2).getTitle());
//
//    }
//
//
//
//    @Test
//    void updateTest() {
//        qnaMapper.deleteAll();
//        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaMapper.insert(qnaDto));
//
//        Long id = qnaMapper.selectAll().get(0).getId();
//        qnaDto.setId(id);
//        qnaDto.setTitle("yes title");
//        qnaDto.setUserId(11111L);
//        assertEquals(1, qnaMapper.update(qnaDto));
//    }
//
//    private boolean compareRelevantFields(QnaDto expected, QnaDto actual) {
//        return Objects.equals(expected.getContents(), actual.getContents()) &&
//                Objects.equals(expected.getTitle(), actual.getTitle()) &&
//                Objects.equals(expected.getUserId(), actual.getUserId());
=======
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
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
//    }
//}