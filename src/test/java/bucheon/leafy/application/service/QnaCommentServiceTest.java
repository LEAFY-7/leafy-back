package bucheon.leafy.application.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QnaCommentServiceTest {
    @Autowired
    QnaCommentService qnaCommentService;
    @Test
    void remove() { qnaCommentService.remove(1L,11111L); }
    @Test
    void write() {

    }
    @Test
    void modify() {

    }
}