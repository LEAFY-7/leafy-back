package bucheon.leafy.application.mapper;//package bucheon.leafy.application.mapper;
//
//import bucheon.leafy.domain.qna.QnaDto;
//import bucheon.leafy.domain.qna.SearchHandler;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//import java.util.List;
//import java.util.Objects;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class QnaMapperTest {
//
//    @Autowired
//    private QnaMapper qnaMapper;
//
//    // 현재 시간
//    @Test
//    void count() {
//        qnaMapper.deleteAll();
//        assertEquals(0, qnaMapper.count());
//
//        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaMapper.insert(qnaDto));
//        assertEquals(1, qnaMapper.count());
//
//        assertEquals(1, qnaMapper.insert(qnaDto));
//        assertEquals(2, qnaMapper.count());
//    }
//
//    @Test
//    void deleteAll() {
//        qnaMapper.deleteAll();
//        assertEquals(0, qnaMapper.count());
//
//        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaMapper.insert(qnaDto));
//
//        assertEquals(1, qnaMapper.deleteAll());
//
//        qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaMapper.insert(qnaDto));
//        assertEquals(1, qnaMapper.insert(qnaDto));
//
//        assertEquals(2, qnaMapper.deleteAll());
//    }
//
//    @Test
//    void deleteTest() {
//        qnaMapper.deleteAll();
//        assertEquals(0, qnaMapper.count());
//
//        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaMapper.insert(qnaDto));
//        Long id = qnaMapper.selectAll().get(0).getId();
//        assertEquals(1, qnaMapper.delete(id, qnaDto.getUserId()));
//        assertEquals(0, qnaMapper.count());
//    }
//
//    @Test
//    void insertTest() {
//        qnaMapper.deleteAll();
//        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaMapper.insert(qnaDto));
//
//        qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaMapper.insert(qnaDto));
//        assertEquals(2, qnaMapper.count());
//
//        qnaMapper.deleteAll();
//        qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaMapper.insert(qnaDto));
//        assertEquals(1, qnaMapper.count());
//    }
//
//    @Test
//    void selectAllTest() {
//        qnaMapper.deleteAll();
//        assertEquals(0, qnaMapper.count());
//
//        List<QnaDto> list = qnaMapper.selectAll();
//        assertEquals(0, list.size());
//
//        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
//        assertEquals(1, qnaMapper.insert(qnaDto));
//
//        list = qnaMapper.selectAll();
//        assertEquals(1, list.size());
//
//        assertEquals(1, qnaMapper.insert(qnaDto));
//        list = qnaMapper.selectAll();
//        assertEquals(2, list.size());
//    }
//
////    @Test
////    void selectTest() {
////        qnaMapper.deleteAll();
////        assertEquals(0, qnaMapper.count());
////
////        QnaDto qnaDto = new QnaDto("false", "contents", 11111L);
////        assertEquals(1, qnaMapper.insert(qnaDto));
////
////        Long id = qnaMapper.selectAll().get(0).getId();
////        QnaDto qnaDtoList = qnaMapper.select(id, userId);
////        System.out.println(qnaMapper.select(id, userId));
////        System.out.println(qnaDtoList);
////
////    }
////
////    @Test
////    void selectPageTest() {
////        qnaMapper.deleteAll();
////
////        for (int i = 1; i <= 10; i++) {
////            QnaDto qnaDto = new QnaDto("dfsgf", ""+i, 11111L);
////            qnaMapper.insert(qnaDto);
////        }
////
////        SearchHandler searchHandler = new SearchHandler(1,5);
////
////        List<QnaDto> list = qnaMapper.searchSelectPage(searchHandler);
////        assertEquals("10", list.get(0).getTitle());
////        assertEquals("9", list.get(1).getTitle());
////        assertEquals("8", list.get(2).getTitle());
////
////    }
//
//
//
//    @Test
//    void updateTest() {
//        qnaMapper.deleteAll();
//        QnaDto qnaDto = new QnaDto("false", "contents", 11111L);
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
//    }
//}