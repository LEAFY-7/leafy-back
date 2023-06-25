package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.qna.QnaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class QnaMapperTest {

    @Autowired
    private QnaMapper qnaMapper;

    @Test
    void deleteAll() throws Exception {

        qnaMapper.deleteAll();

        QnaDto qnaDto = new QnaDto("no title", "no content", 1);
        assertTrue(qnaMapper.insert(qnaDto)==1);
        assertTrue(qnaMapper.deleteAll()==1);


        qnaDto = new QnaDto("no title", "no content", 1);
        assertTrue(qnaMapper.insert(qnaDto)==1);
        assertTrue(qnaMapper.insert(qnaDto)==1);
        assertTrue(qnaMapper.deleteAll()==2);

    }

    @Test
    void select() {
    }

    @Test
    void delete() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void selectPage() {
    }

    @Test
    void selectAll() {
    }

    @Test
    void searchSelectPage() {
    }

    @Test
    void searchResultCnt() {
    }
}