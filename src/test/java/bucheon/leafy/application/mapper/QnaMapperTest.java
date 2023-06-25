//package bucheon.leafy.application.mapper;
//
//import bucheon.leafy.domain.qna.QnaDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//@AutoConfigureMockMvc
//class QnaMapperTest extends IntegrationTestSupport {
//
//    @Autowired
//    private QnaMapper qnaMapper;
//
//    @Test
//    void deleteAll() throws Exception {
//        qnaMapper.deleteAll();
//        assertTrue(qnaMapper.count()==0);
//
//        QnaDto qnaDto = new QnaDto("no title", "no content", 1);
//        assertTrue(qnaMapper.insert(qnaDto)==1);
//        assertTrue(qnaMapper.deleteAll()==1);
//
//
//        qnaDto = new QnaDto("no title", "no content", 1);
//        assertTrue(qnaMapper.insert(qnaDto)==1);
//        assertTrue(qnaMapper.insert(qnaDto)==1);
//        assertTrue(qnaMapper.deleteAll()==2);
//
//    }
//
//}