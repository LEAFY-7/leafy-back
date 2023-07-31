package bucheon.leafy.application.service;//package bucheon.leafy.application.service;
//
//import bucheon.leafy.application.mapper.QnaMapper;
//import bucheon.leafy.domain.qna.QnaDto;
//import bucheon.leafy.domain.qna.SearchHandler;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//
<<<<<<< HEAD
//
//import java.util.List;
=======
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
//import java.util.Objects;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class QnaServiceTest {
//
//    @Autowired
//    private QnaService qnaService;
//
//    @Autowired
//    private QnaMapper qnaMapper;
//
<<<<<<< HEAD
=======
//    // 현재 시간
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
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
<<<<<<< HEAD
////    @Test
////    void deleteTest() {
////
////        qnaMapper.deleteAll();
////        assertEquals(0, qnaService.getCount());
////
////        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
////        assertEquals(1, qnaService.write(qnaDto));
////        Long id = qnaService.getList().get(0).getId();
////
////        QnaDto qnaDtoList = qnaService.getRead(id, userId);
////
////        assertTrue(qnaDtoList.getIsDelete());
////    }
=======
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
//
//    }
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
//
//    @Test
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
<<<<<<< HEAD
////    @Test
////    void selectTest() {
////        qnaMapper.deleteAll();
////        assertEquals(0, qnaService.getCount());
////
////        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
////        assertEquals(1, qnaService.write(qnaDto));
////
////        Long id = qnaService.getList().get(0).getId();
////        QnaDto qnaDtoResult = qnaService.getRead(id, userId);
////
////        assertNotNull(qnaDtoResult);
////        assertTrue(compareRelevantFields(qnaDto, qnaDtoResult));
////    }
=======
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
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
//
//    @Test
//    void selectPageTest() {
//        qnaMapper.deleteAll();
//
//        for (int i = 1; i <= 10; i++) {
<<<<<<< HEAD
//            QnaDto qnaDto = new QnaDto("dfsgf", "" + i, 11111L);
//            qnaService.write(qnaDto);
//        }
//
//        SearchHandler searchHandler = new SearchHandler(1, 5);
//
//    }
//
////    @Test
////    void updateTest() {
////        qnaMapper.deleteAll();
////        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
////        assertEquals(1, qnaMapper.insert(qnaDto));
////
////        Long id = qnaMapper.selectAll().get(0).getId();
////        qnaDto.setId(id);
////        qnaDto.setTitle("yes title");
////        qnaDto.setUserId(11111L);
////        assertEquals(1, qnaMapper.update(qnaDto));
////    }
=======
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
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
//
//    private boolean compareRelevantFields(QnaDto expected, QnaDto actual) {
//        return Objects.equals(expected.getContents(), actual.getContents()) &&
//                Objects.equals(expected.getTitle(), actual.getTitle()) &&
//                Objects.equals(expected.getUserId(), actual.getUserId());
//    }
//}