package bucheon.leafy.application.mapper;




import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QnaReplyMapperTest {

    @Autowired
    QnaReplyMapper qnaReplyMapper;
    @Test
    void count() throws Exception {
        qnaReplyMapper.deleteAllReply();
        assertTrue(qnaReplyMapper.count(1)==0);
    }

    @Test
    void delete() {
    }

    @Test
    void insert() {
    }

    @Test
    void selectAll() {
    }

    @Test
    void update() {
    }

    @Test
    void updateCommentCnt() {
    }

    @Test
    void increaseviewcntQnaReply() {
    }
}